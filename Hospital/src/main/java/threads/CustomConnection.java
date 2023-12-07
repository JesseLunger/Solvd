package threads;

public class CustomConnection {

    public void release() {
        ConnectionPool.getInstance().releaseConnection(this);
    }
}
