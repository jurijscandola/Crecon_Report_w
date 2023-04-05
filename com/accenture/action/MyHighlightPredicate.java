package  action;

import org.jdesktop.swingx.decorator.ComponentAdapter;
import java.awt.Component;
import org.jdesktop.swingx.decorator.HighlightPredicate;

public class MyHighlightPredicate implements HighlightPredicate
{
    public boolean isHighlighted(final Component renderer, final ComponentAdapter adapter) {
        final String item = (String)adapter.getValueAt(adapter.row, 1);
        return this.testItem(item);
    }

    public boolean testItem(final String item) {
        return "".equals(item) || null == item;
    }
}
