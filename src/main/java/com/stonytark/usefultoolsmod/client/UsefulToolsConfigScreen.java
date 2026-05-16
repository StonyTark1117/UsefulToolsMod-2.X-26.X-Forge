package com.stonytark.usefultoolsmod.client;

import com.electronwill.nightconfig.core.UnmodifiableConfig;
import com.stonytark.usefultoolsmod.Config;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.impl.builders.SubCategoryBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.Map;

public final class UsefulToolsConfigScreen {
    private UsefulToolsConfigScreen() {}

    public static Screen build(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Component.literal("The Useful Tools Mod"))
                .setSavingRunnable(Config.SPEC::save);

        ConfigEntryBuilder eb = builder.entryBuilder();
        UnmodifiableConfig root = Config.SPEC.getValues();

        for (Map.Entry<String, Object> entry : root.valueMap().entrySet()) {
            String name = entry.getKey();
            Object val = entry.getValue();

            if (val instanceof UnmodifiableConfig section) {
                ConfigCategory cat = builder.getOrCreateCategory(Component.literal(prettify(name)));
                addSection(cat, eb, section);
            } else if (val instanceof ForgeConfigSpec.ConfigValue<?> cv) {
                ConfigCategory cat = builder.getOrCreateCategory(Component.literal("General"));
                cat.addEntry(makeEntry(eb, cv, name));
            }
        }

        if (root.valueMap().isEmpty()) {
            builder.getOrCreateCategory(Component.literal("General"));
        }

        return builder.build();
    }

    private static void addSection(ConfigCategory cat, ConfigEntryBuilder eb, UnmodifiableConfig section) {
        for (Map.Entry<String, Object> entry : section.valueMap().entrySet()) {
            String key = entry.getKey();
            Object val = entry.getValue();
            if (val instanceof ForgeConfigSpec.ConfigValue<?> cv) {
                cat.addEntry(makeEntry(eb, cv, key));
            } else if (val instanceof UnmodifiableConfig sub) {
                SubCategoryBuilder sb = eb.startSubCategory(Component.literal(prettify(key)));
                for (Map.Entry<String, Object> e2 : sub.valueMap().entrySet()) {
                    if (e2.getValue() instanceof ForgeConfigSpec.ConfigValue<?> cv2) {
                        sb.add(makeEntry(eb, cv2, e2.getKey()));
                    }
                }
                cat.addEntry(sb.build());
            }
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static me.shedaniel.clothconfig2.api.AbstractConfigListEntry<?> makeEntry(
            ConfigEntryBuilder eb, ForgeConfigSpec.ConfigValue<?> cv, String label) {
        Object value = cv.get();
        Object def = cv.getDefault();
        Component name = Component.literal(prettify(label));

        if (value instanceof Boolean b) {
            boolean dv = def instanceof Boolean db ? db : true;
            return eb.startBooleanToggle(name, b)
                    .setDefaultValue(dv)
                    .setSaveConsumer(((ForgeConfigSpec.BooleanValue) cv)::set)
                    .build();
        } else if (value instanceof Integer i) {
            int dv = def instanceof Integer di ? di : 0;
            return eb.startIntField(name, i)
                    .setDefaultValue(dv)
                    .setSaveConsumer(((ForgeConfigSpec.IntValue) cv)::set)
                    .build();
        } else if (value instanceof Long l) {
            long dv = def instanceof Long dl ? dl : 0L;
            return eb.startLongField(name, l)
                    .setDefaultValue(dv)
                    .setSaveConsumer(((ForgeConfigSpec.LongValue) cv)::set)
                    .build();
        } else if (value instanceof Double d) {
            double dv = def instanceof Double dd ? dd : 0.0;
            return eb.startDoubleField(name, d)
                    .setDefaultValue(dv)
                    .setSaveConsumer(((ForgeConfigSpec.DoubleValue) cv)::set)
                    .build();
        } else if (value instanceof String s) {
            String dv = def instanceof String ds ? ds : "";
            return eb.startStrField(name, s)
                    .setDefaultValue(dv)
                    .setSaveConsumer(((ForgeConfigSpec.ConfigValue<String>) cv)::set)
                    .build();
        }

        return eb.startStrField(name, String.valueOf(value))
                .setDefaultValue(String.valueOf(def))
                .build();
    }

    private static String prettify(String raw) {
        StringBuilder out = new StringBuilder(raw.length() + 4);
        boolean capNext = true;
        for (int i = 0; i < raw.length(); i++) {
            char c = raw.charAt(i);
            if (c == '_' || c == '-') {
                out.append(' ');
                capNext = true;
            } else if (Character.isUpperCase(c) && i > 0 && Character.isLowerCase(raw.charAt(i - 1))) {
                out.append(' ').append(c);
                capNext = false;
            } else if (capNext) {
                out.append(Character.toUpperCase(c));
                capNext = false;
            } else {
                out.append(c);
            }
        }
        return out.toString();
    }
}
