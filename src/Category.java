import java.util.HashSet;
import java.util.Set;

public class Category {
    private Category parent;
    private String name;

    private Set<String> keywords = new HashSet();

    // Init category with name and parent
    public Category(Category parent, String name) {
        this.parent = parent;
        this.name = name;
    }

    public Boolean hasKeyword(String keyword) {
        if (keywords.contains(keyword)) {
            return true;
        }

        if (parent != null) {
            return parent.hasKeyword(keyword);
        }

        return false;
    }

    public Integer getLevelNumber() {
        if (parent != null) {
            return parent.getLevelNumber() + 1;
        }
        return 1;
    }

    public String getLevel() {
        if (parent != null) {
            return parent.getLevel() + " - " + this.name;
        }
        return this.name;
    }

    public void addKeyword(String keyword) {
        if (keyword != null && !keyword.isEmpty() && !keyword.isBlank()) {
            this.keywords.add(keyword);
        }
    }

    public String getName() {
        return name;
    }

    public Set<String> getKeywords() {
        Set<String> result = new HashSet<>();
        if (parent != null) {
            result.addAll(parent.getKeywords());
        }
        result.addAll(keywords);
        return result;
    }
}
