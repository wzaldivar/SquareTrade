import java.util.ArrayList;
import java.util.Set;

public class SquareTrade {

    private ArrayList<Category> categories = new ArrayList<>();

    // Return category by keyword with the lowest level
    public Category getCategoryByKeyword(String keyword) {
        Category result = null;
        Integer resultLevel = 0;

        for (Category category : categories) {
            if (category.hasKeyword(keyword)) {
                Integer currentLevel = category.getLevelNumber();
                if (result == null) {
                    result = category;
                    resultLevel = currentLevel;
                } else if (resultLevel > currentLevel) {
                    result = category;
                    resultLevel = currentLevel;
                }
            }
        }

        return result;
    }

    private void Tests() throws Exception {
        Category root = new Category(null, "Root");
        root.addKeyword("root");
        root.addKeyword("parent");
        root.addKeyword("origin");

        Category furniture = new Category(root, "Furniture");
        furniture.addKeyword("furniture");

        Category electronics = new Category(root, "Electronics");
        electronics.addKeyword("electronics");
        electronics.addKeyword("computers");

        Category homeAppliances = new Category(root, "Home Appliances");
        homeAppliances.addKeyword("HomeAppliances");

        Category majorAppliances = new Category(homeAppliances, "Major Appliances");
        majorAppliances.addKeyword("MajorAppliances");

        Category minorAppliances = new Category(homeAppliances, "Minor Appliances");
        minorAppliances.addKeyword("MinorAppliances");

        Category lawnAndGarden = new Category(homeAppliances, "Lawn & Garden");
        lawnAndGarden.addKeyword("Lawn");
        lawnAndGarden.addKeyword("Garden");
        lawnAndGarden.addKeyword("GardeningTools");


        Category kitchenAppliances = new Category(majorAppliances, "Kitchen Appliances");
        kitchenAppliances.addKeyword("KitchenAppliances");
        kitchenAppliances.addKeyword("spoon");

        Category generalAppliances = new Category(majorAppliances, "General Appliances");
        generalAppliances.addKeyword("GeneralAppliances");

        categories.add(root);
        categories.add(furniture);
        categories.add(electronics);
        categories.add(homeAppliances);
        categories.add(majorAppliances);
        categories.add(minorAppliances);
        categories.add(lawnAndGarden);
        categories.add(kitchenAppliances);
        categories.add(generalAppliances);

        if (root.getLevelNumber() != 1) {
            throw new Exception("Bad root level number");
        }

        if (furniture.getLevelNumber() != 2) {
            throw new Exception("Bad furniture level number");
        }


        System.out.println(generalAppliances.getLevel());
        if (!generalAppliances.getLevel().equals("Root - Home Appliances - Major Appliances - General Appliances")) {
            throw new Exception("Expected level for generalAppliances: Root - Home Appliances - Major Appliances - General Appliances");
        }

        if (!electronics.hasKeyword("electronics")) {
            throw new Exception("Error searching keywords");
        }

        if (!electronics.hasKeyword("root")) {
            throw new Exception("Error searching parent keywords");
        }

        if (electronics.hasKeyword("unknown")) {
            throw new Exception("Can't have unknown keyword");
        }

        Category retrieved = this.getCategoryByKeyword("GardeningTools");

        if (retrieved != lawnAndGarden) {
            throw new Exception("Expected " + lawnAndGarden.getName() + " retrieved: " + retrieved.getName());
        }

        retrieved = this.getCategoryByKeyword("HomeAppliances");
        if (retrieved != homeAppliances && retrieved.hasKeyword("HomeAppliances")) {
            throw new Exception("Error getting the lowest level " + retrieved.getLevel());
        }

        retrieved = this.getCategoryByKeyword("root");
        if (retrieved != root && retrieved.hasKeyword("root")) {
            throw new Exception("Error getting the lowest level " + retrieved.getLevel());
        }

        Set<String> keywords = kitchenAppliances.getKeywords();
        if(!keywords.contains("root") || !keywords.contains("spoon")){
            throw new Exception("Error retrieving keywords from all levels");
        }
    }

    public static void main(String[] args) throws Exception {
        SquareTrade squareTrade = new SquareTrade();
        squareTrade.Tests();
    }
}