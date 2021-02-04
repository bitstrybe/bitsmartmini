
package bt.bitsmartmini.tablemodel;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Justus O Adams
 */
public class CategoryTableModel {

    private SimpleStringProperty categoryName;

    public CategoryTableModel() {
    }

    public CategoryTableModel(String categoryName) {
        this.categoryName = new SimpleStringProperty(categoryName);
    }

    public String getCategoryName() {
        return categoryName.get();
    }

    public SimpleStringProperty getCategoryNameProperty() {
        return categoryName;
    }

    public void setCategoryNameProperty(String categoryName) {
        this.categoryName = new SimpleStringProperty(categoryName);
    }

}
