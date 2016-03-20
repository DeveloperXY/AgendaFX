package gui.listeners;

/**
 * Created by Mohammed Aouf ZOUAG on 20/03/2016.
 *
 * Any class that implements this interface is a "data bridge" between
 * the @source and the @destination.
 */
public interface DataBridge {
    public void setSaveListener(SaveListener listener);
    public void setLoadListener(LoadListener listener);
}
