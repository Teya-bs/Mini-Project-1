package com.example.miniproject2.models;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Book  {

        private final SimpleStringProperty name;
        private final SimpleIntegerProperty page;
        private final SimpleStringProperty author;
        public Book(String name, int page, String author) {
            this.name = new SimpleStringProperty(name);
            this.page = new SimpleIntegerProperty(page);
            this.author = new SimpleStringProperty(author);
        }
        public String getName() {
            return name.get();
        }
        public void setName(String name) {
            this.name.set(name);
        }
        public SimpleStringProperty nameProperty(){
            return this.name;
        }
        public int getPage() {
            return page.get();
        }
        public void setPage(int page) {
            this.page.set(page);
        }
        public SimpleIntegerProperty pageProperty(){
            return this.page;
        }
        public String getAuthor() {
            return author.get();
        }
        public void setAuthor(String author) {
            this.author.set(author);
        }
        public SimpleStringProperty authorProperty(){
            return this.author;
        }
    }




