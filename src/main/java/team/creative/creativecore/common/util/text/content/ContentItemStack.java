package team.creative.creativecore.common.util.text.content;

import java.util.Optional;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.StringSplitter.WidthProvider;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.ItemStack;
import team.creative.creativecore.client.render.GuiRenderHelper;

public class ContentItemStack implements AdvancedContent {
    
    public final ItemStack stack;
    
    public ContentItemStack(ItemStack stack) {
        this.stack = stack;
    }
    
    @Override
    public <T> Optional<T> visit(FormattedText.StyledContentConsumer<T> consumer, Style style) {
        return stack.getHoverName().visit(consumer, style);
    }
    
    @Override
    public <T> Optional<T> visit(FormattedText.ContentConsumer<T> consumer) {
        return stack.getHoverName().visit(consumer);
    }
    
    @Override
    public int width(WidthProvider widthProvider, Style style) {
        return 14;
    }
    
    @Override
    public int height() {
        return 8;
    }
    
    @Override
    public FormattedText asText() {
        return new ContentItemStackText(this);
    }
    
    private static record ContentItemStackText(ContentItemStack content) implements AdvancedFormattedText {
        
        @Override
        public int width(WidthProvider widthProvider, Style style) {
            return content.width(widthProvider, style);
        }
        
        @Override
        public int height() {
            return content.height();
        }
        
        @Override
        public void render(PoseStack pose, int defaultColor) {
            pose.pushPose();
            
            pose.translate(0, -2, 10);
            pose.scale(0.8F, 0.8F, 0.8F);
            GuiRenderHelper.drawItemStack(pose, content.stack, 1);
            pose.popPose();
        }
        
    }
    
}
