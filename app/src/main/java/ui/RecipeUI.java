package ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

import data.RecipeFileHandler;

public class RecipeUI {
    private BufferedReader reader;
    private RecipeFileHandler fileHandler;

    public RecipeUI() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        fileHandler = new RecipeFileHandler();
    }

    public RecipeUI(BufferedReader reader, RecipeFileHandler fileHandler) {
        this.reader = reader;
        this.fileHandler = fileHandler;
    }

    public void displayMenu() {
        while (true) {
            try {
                System.out.println();
                System.out.println("Main Menu:");
                System.out.println("1: Display Recipes");
                System.out.println("2: Add New Recipe");
                System.out.println("3: Search Recipe");
                System.out.println("4: Exit Application");
                System.out.print("Please choose an option: ");

                String choice = reader.readLine();

                switch (choice) {
                    case "1":
                        // 設問1: 一覧表示機能
                        displayRecipes();
                        break;
                    case "2":
                        // 設問2: 新規登録機能
                        addNewRecipe();
                        break;
                    case "3":
                        // 設問3: 検索機能
                        break;
                    case "4":
                        System.out.println("Exit the application.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Error reading input from user: " + e.getMessage());
            }
        }
    }

    /**
     * 設問1: 一覧表示機能
     * RecipeFileHandlerから読み込んだレシピデータを整形してコンソールに表示します。
     */
    private void displayRecipes() {
        // ArrayListの定義
        ArrayList<String> recipes = fileHandler.readRecipes();
        System.out.println("Recipes:");
        // recipeが空の時の処理
        if (recipes.isEmpty()) {
            System.out.println("No recipes available.");
        } else {
            // for拡張文を使用しループさせる
            for (String result : recipes) {
                // ,区切りでレシピ、材料名を分けて出力内容に沿って出力する
                String[] recipe = result.split(",", 2);
                System.out.println("-----------------------------------");
                System.out.println("Recipe Name: " + recipe[0]);
                System.out.println("Main Ingredients: " + recipe[1]);
            }
        }
    }

    /**
     * 設問2: 新規登録機能
     * ユーザーからレシピ名と主な材料を入力させ、RecipeFileHandlerを使用してrecipes.txtに新しいレシピを追加します。
     *
     * @throws java.io.IOException 入出力が受け付けられない
     */
    private void addNewRecipe() throws IOException {
        // 入力の受付処理
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Enter recipe name:" );
            String recipe = reader.readLine();
            System.out.print("Enter main ingredients (comma separated):");// 材料名はユーザーが","区切りで入力する
            String ingredients = reader.readLine();
            // 入力終了後、
            System.out.println("Recipe added succesfully.");
            // fileHandlerクラスのaddRecipeのメソッドを呼び出し、引数でレシピ・材料名を受け渡す
            fileHandler.addRecipe(recipe, ingredients);
        } catch (IOException e) {
            throw new IOException("\"Error reading file: 例外のメッセージ\"");
        }
    }

    /**
     * 設問3: 検索機能
     * ユーザーから検索クエリを入力させ、そのクエリに基づいてレシピを検索し、一致するレシピをコンソールに表示します。
     *
     * @throws java.io.IOException 入出力が受け付けられない
     */
    // private void searchRecipe() throws IOException {

    // }

}
