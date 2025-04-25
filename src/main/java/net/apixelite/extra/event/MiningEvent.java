package net.apixelite.extra.event;

import net.apixelite.extra.entity.attribute.ModEntityAttributes;
import net.apixelite.extra.util.ModTags;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MiningEvent implements PlayerBlockBreakEvents.Before {
    private static final Set<BlockPos> HARVESTED_BLOCKS = new HashSet<>();

    @Override
    public boolean beforeBlockBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity) {
        ItemStack mainHandItem = player.getMainHandStack();
        double mining_spread = player.getAttributeValue(ModEntityAttributes.MINING_SPREAD);
        double mining_depth = player.getAttributeValue(ModEntityAttributes.MINING_DEPTH);
        double deforestation = player.getAttributeValue(ModEntityAttributes.DEFORESTATION);
        double vein_miner = player.getAttributeValue(ModEntityAttributes.VEIN_MINER);

        if (player instanceof ServerPlayerEntity serverPlayer) {
            if (HARVESTED_BLOCKS.contains(pos)) {
                return true;
            }

            if ((mainHandItem.isIn(ItemTags.AXES) && deforestation > 0 && (state.isIn(ModTags.Blocks.DEFORESTABLE))) ||
                    (mainHandItem.isIn(ItemTags.PICKAXES) && vein_miner > 0 && (state.isIn(ModTags.Blocks.VEIN_MINEABLE)))
            ) {
                List<BlockPos> list = new ArrayList<>();
                for (BlockPos position : getLogsOresToBeDestroyedTest(list, world, serverPlayer, serverPlayer.getMainHandStack(), world.getBlockState(pos), pos)) {
                    if (pos == position || !mainHandItem.getItem().isCorrectForDrops(mainHandItem, world.getBlockState(position))) {
                        continue;
                    }
                    harvestBlocks(position, serverPlayer);
                }
            }

            if (mainHandItem.isIn(ModTags.Items.MINING_TOOLS)) {
                for (BlockPos position : getBlocksToBeDestroyed(((int) mining_spread), ((int) mining_depth), pos, serverPlayer)) {
                    if (pos == position || !mainHandItem.getItem().isCorrectForDrops(mainHandItem, world.getBlockState(position))) {
                        continue;
                    }
                    harvestBlocks(position, serverPlayer);
                }
            }
        }
        return true;
    }

    private static void harvestBlocks(BlockPos pos, ServerPlayerEntity serverPlayer) {
        MiningEvent.HARVESTED_BLOCKS.add(pos);
        serverPlayer.interactionManager.tryBreakBlock(pos);
        MiningEvent.HARVESTED_BLOCKS.remove(pos);
    }

    private static List<BlockPos> getBlocksToBeDestroyed(int range, int thickness, BlockPos initalBlockPos, ServerPlayerEntity player) {
        List<BlockPos> positions = new ArrayList<>();
        HitResult hit = player.raycast(20, 0, false);
        if (hit.getType() == HitResult.Type.BLOCK) {
            BlockHitResult blockHit = (BlockHitResult) hit;


            if(blockHit.getSide() == Direction.DOWN || blockHit.getSide() == Direction.UP) {
                for (int y = 0; y <= thickness; y++) {
                    int depth = blockHit.getSide() == Direction.UP ? initalBlockPos.getY() - y : initalBlockPos.getY() + y;
                    BlockPos blockPos = new BlockPos(initalBlockPos.getX(), depth, initalBlockPos.getZ());
                    getBlocksUpDown(range, blockPos, positions);
                }
            }

            if(blockHit.getSide() == Direction.NORTH || blockHit.getSide() == Direction.SOUTH) {
                for (int z = 0; z <= thickness; z++) {
                    int depth = blockHit.getSide() == Direction.NORTH ? initalBlockPos.getZ() + z : initalBlockPos.getZ() - z;
                    BlockPos blockPos = new BlockPos(initalBlockPos.getX(), initalBlockPos.getY(), depth);
                    getBlocksNorthSouth(range, blockPos, positions);
                }

            }

            if(blockHit.getSide() == Direction.EAST || blockHit.getSide() == Direction.WEST) {
                for (int x = 0; x <= thickness; x++) {
                    int depth = blockHit.getSide() == Direction.EAST ? initalBlockPos.getX() - x : initalBlockPos.getX() + x;
                    BlockPos blockPos = new BlockPos(depth, initalBlockPos.getY(), initalBlockPos.getZ());
                    getBlocksEastWest(range, blockPos, positions);
                }
            }
        }

        return positions;
    }

    private static void getBlocksUpDown(int range, BlockPos initalBlockPos, List<BlockPos> positions) {
        for(int x = -range; x <= range; x++) {
            for(int y = -range; y <= range; y++) {
                positions.add(new BlockPos(initalBlockPos.getX() + x, initalBlockPos.getY(), initalBlockPos.getZ() + y));
            }
        }
    }

    private static void getBlocksNorthSouth(int range, BlockPos initalBlockPos, List<BlockPos> positions) {
            for(int x = -range; x <= range; x++) {
                for(int y = -range; y <= range; y++) {
                    positions.add(new BlockPos(initalBlockPos.getX() + x, initalBlockPos.getY() + y, initalBlockPos.getZ()));
                }
            }
    }

    private static void getBlocksEastWest(int range, BlockPos initalBlockPos, List<BlockPos> positions) {
        for(int x = -range; x <= range; x++) {
            for(int y = -range; y <= range; y++) {
                positions.add(new BlockPos(initalBlockPos.getX(), initalBlockPos.getY() + y, initalBlockPos.getZ() + x));
            }
        }
    }

    private static List<BlockPos> getVeinShape(BlockPos pos) {
        return List.of(
                pos.up(), pos.down(),
                pos.north(), pos.south(),
                pos.east(), pos.west()
        );
    }
    private static List<BlockPos> getTreeShape(BlockPos pos) {
        List<BlockPos> positions = new ArrayList<>();

        for (int y = -1; y <= 1; y++)
            for(int x = -1; x <= 1; x++) {
                for(int z = -1; z <= 1; z++) {
                    positions.add(new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z));
                }
            }
        return positions;
    }

    private static List<BlockPos> getLogsOresToBeDestroyedTest(
            List<BlockPos> positions,
            World world,
            ServerPlayerEntity player,
            ItemStack stack,
            BlockState initalState,
            BlockPos initalBlockPos
    ) {
        int maxBlocks = stack.isIn(ItemTags.PICKAXES) ?
                ((int) player.getAttributeValue(ModEntityAttributes.VEIN_MINER)) :
                ((int) player.getAttributeValue(ModEntityAttributes.DEFORESTATION));

        HitResult hit = player.raycast(20, 0, false);

        if (hit.getType() == HitResult.Type.BLOCK) {
            List<BlockPos> posToCheck = stack.isIn(ItemTags.PICKAXES) ? getVeinShape(initalBlockPos) : getTreeShape(initalBlockPos);
            for (BlockPos pos : posToCheck) {
                if (positions.size() >= maxBlocks || stack.getDamage() + positions.size() == stack.getMaxDamage()) {
                    break;
                }

                if (positions.contains(pos)) {
                    continue;
                }

                BlockState state = world.getBlockState(pos);
                Block block = state.getBlock();

                if (!block.equals(initalState.getBlock()) || !stack.isSuitableFor(state)) {
                    continue;
                }

                positions.add(pos);

                getLogsOresToBeDestroyedTest(positions, world, player, stack, initalState, pos);
            }
        }

        return positions;
    }



}
