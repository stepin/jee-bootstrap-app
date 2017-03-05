package name.stepin.templateapp.utils;


import lombok.ToString;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.*;

@SessionScoped
@ToString
public class FlashStore implements Serializable {
    public static final long serialVersionUID = 1L;

    private static final String ERROR = "error";
    private static final String WARNING = "warning";
    private static final String NOTICE = "notice";

    private Map<String, List<String>> map = new HashMap<>();

    public void addError(String message) {
        addMessage(ERROR, message);
    }

    public void addWarning(String message) {
        addMessage(WARNING, message);
    }

    public void addNotice(String message) {
        addMessage(NOTICE, message);
    }

    public void addMessage(String messageType, String message) {
        Objects.requireNonNull(message);
        List<String> messages = getMessages(messageType);
        messages.add(message);
    }

    public List<String> getErrors() {
        return getMessages(ERROR);
    }

    public List<String> getWarnings() {
        return getMessages(WARNING);
    }

    public List<String> getNotices() {
        return getMessages(NOTICE);
    }

    public List<String> getMessages(String messageType) {
        Objects.requireNonNull(messageType);
        return map.getOrDefault(messageType, new ArrayList<>());
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public void clear() {
        map.clear();
    }
}
