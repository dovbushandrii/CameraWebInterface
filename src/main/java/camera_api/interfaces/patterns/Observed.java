package camera_api.interfaces.patterns;

public interface Observed {
    void notifyAllObservers();

    void addObserver(Observer observer);

    void removeObserver(Observer observer);
}
