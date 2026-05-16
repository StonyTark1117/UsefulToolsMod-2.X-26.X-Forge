package com.stonytark.usefultoolsmod.block.entity;

import com.stonytark.usefultoolsmod.block.ModBlocks;
import com.stonytark.usefultoolsmod.item.ModItems;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

public class SpectralInfuserMenu extends AbstractContainerMenu {
    private final SpectralInfuserBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;

    // Slot indices
    private static final int PLAYER_INV_START = 0;
    private static final int PLAYER_INV_END = 27;
    private static final int PLAYER_HOTBAR_END = 36;
    private static final int INPUT_SLOT = 36;
    private static final int FUEL_SLOT = 37;
    private static final int OUTPUT_SLOT = 38;

    // Server-side constructor
    public SpectralInfuserMenu(int containerId, Inventory inv,
                               SpectralInfuserBlockEntity be, ContainerData data) {
        super(ModMenuTypes.SPECTRAL_INFUSER_MENU.get(), containerId);
        this.blockEntity = be;
        this.level = inv.player.level();
        this.data = data;

        addPlayerInventory(inv);
        addPlayerHotbar(inv);

        // Block entity slots — use the Container directly
        this.addSlot(new Slot(be, 0, 56, 17) {                                  // input (top)
            @Override
            public boolean mayPlace(ItemStack stack) {
                return be.isItemValidForSlot(0, stack);
            }
        });
        this.addSlot(new Slot(be, 1, 56, 53) {                                  // ectoplasm (bottom)
            @Override
            public boolean mayPlace(ItemStack stack) {
                return be.isItemValidForSlot(1, stack);
            }
        });
        this.addSlot(new Slot(be, 2, 116, 35) {                                 // output (right)
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false; // output-only
            }
        });

        addDataSlots(data);
    }

    // Client-side constructor (from network)
    public SpectralInfuserMenu(int containerId, Inventory inv, FriendlyByteBuf extraData) {
        this(containerId, inv, getBlockEntity(inv, extraData), new SimpleContainerData(2));
    }

    private static SpectralInfuserBlockEntity getBlockEntity(Inventory inv, FriendlyByteBuf data) {
        BlockEntity be = inv.player.level().getBlockEntity(data.readBlockPos());
        if (be instanceof SpectralInfuserBlockEntity sibe) {
            return sibe;
        }
        throw new IllegalStateException("Block entity is not correct at " + be);
    }

    public boolean isCrafting() {
        return data.get(0) > 0;
    }

    public int getScaledProgress() {
        int progress = data.get(0);
        int maxProgress = data.get(1);
        int arrowPixelWidth = 24;
        return maxProgress != 0 ? progress * arrowPixelWidth / maxProgress : 0;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack original = slot.getItem();
            newStack = original.copy();

            if (index == OUTPUT_SLOT) {
                // Output → player inventory
                if (!this.moveItemStackTo(original, PLAYER_INV_START, PLAYER_HOTBAR_END, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(original, newStack);
            } else if (index >= INPUT_SLOT) {
                // Input/fuel → player inventory
                if (!this.moveItemStackTo(original, PLAYER_INV_START, PLAYER_HOTBAR_END, false)) {
                    return ItemStack.EMPTY;
                }
            } else {
                // Player inventory → block entity slots
                if (SpectralInfuserBlockEntity.isInfusable(original)) {
                    if (!this.moveItemStackTo(original, INPUT_SLOT, INPUT_SLOT + 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (original.is(ModItems.ECTOPLASM.get())) {
                    if (!this.moveItemStackTo(original, FUEL_SLOT, FUEL_SLOT + 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index < PLAYER_INV_END) {
                    // Main inventory → hotbar
                    if (!this.moveItemStackTo(original, PLAYER_INV_END, PLAYER_HOTBAR_END, false)) {
                        return ItemStack.EMPTY;
                    }
                } else {
                    // Hotbar → main inventory
                    if (!this.moveItemStackTo(original, PLAYER_INV_START, PLAYER_INV_END, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            }

            if (original.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (original.getCount() == newStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, original);
        }
        return newStack;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                player, ModBlocks.SPECTRAL_INFUSER.get());
    }

    private void addPlayerInventory(Inventory inv) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(inv, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory inv) {
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(inv, col, 8 + col * 18, 142));
        }
    }
}
