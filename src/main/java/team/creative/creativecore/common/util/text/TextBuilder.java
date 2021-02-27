package team.creative.creativecore.common.util.text;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import team.creative.creativecore.common.gui.GuiControl;
import team.creative.creativecore.common.util.mc.ColorUtils;

public class TextBuilder {
    
    private static NumberFormat format = NumberFormat.getNumberInstance(Locale.US);
    private final List<ITextComponent> components = new ArrayList<>();
    
    public TextBuilder() {
        
    }
    
    public TextBuilder add(ITextComponent component) {
        if (components.isEmpty())
            components.add(component);
        else {
            ITextComponent last = components.get(components.size() - 1);
            if (last instanceof TextComponent)
                ((TextComponent) last).append(component);
            else
                components.add(component);
        }
        return this;
    }
    
    public TextBuilder(String text) {
        text(text);
    }
    
    public TextBuilder translateIfCan(String text) {
        String translated = GuiControl.translate(text);
        if (!translated.equals(text))
            add(new TranslationTextComponent(text));
        return this;
    }
    
    public TextBuilder translate(String text) {
        add(new TranslationTextComponent(text));
        return this;
    }
    
    public TextBuilder translate(String text, Object... param) {
        add(new TranslationTextComponent(text, param));
        return this;
    }
    
    public TextBuilder text(String text) {
        add(new StringTextComponent(text));
        return this;
    }
    
    public TextBuilder number(float number) {
        return number(number, false);
    }
    
    public TextBuilder number(float number, boolean rounded) {
        if (rounded)
            text("" + Math.round(number));
        else
            text("" + format.format(number));
        return this;
    }
    
    public TextBuilder number(double number) {
        return number(number, false);
    }
    
    public TextBuilder number(double number, boolean rounded) {
        if (rounded)
            text("" + Math.round(number));
        else
            text("" + format.format(number));
        return this;
    }
    
    public TextBuilder newLine() {
        components.add(new LinebreakTextComponent());
        return this;
    }
    
    public TextBuilder color(int color) {
        int r = ColorUtils.getRed(color);
        int g = ColorUtils.getGreen(color);
        int b = ColorUtils.getBlue(color);
        int a = ColorUtils.getAlpha(color);
        text("" + TextFormatting.RED + r + " " + TextFormatting.GREEN + g + " " + TextFormatting.BLUE + b + (a < 255 ? " " + TextFormatting.WHITE + a : ""));
        return this;
    }
    
    public TextBuilder stack(ItemStack stack) {
        add(new ItemStackTextComponent(stack));
        return this;
    }
    
    public List<ITextComponent> build() {
        return components;
    }
    
}