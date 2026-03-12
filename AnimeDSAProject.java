import java.util.*;

// Anime Data Model
// CO2: Designing an Abstract Data Type (ADT) using a class to store anime information
class Anime {

    String title;
    String genre;
    String status;

    // CO2: Constructor initializes the Anime object
    Anime(String title, String genre, String status) {
        this.title = title;
        this.genre = genre;
        this.status = status;
    }

    // CO5: Practical application method to display anime details
    void display() {
        System.out.println("Title  : " + title);
        System.out.println("Genre  : " + genre);
        System.out.println("Status : " + status);
        System.out.println("--------------------------");
    }
}

public class AnimeDSAProject {

    // CO2: Linked List used as List ADT to store anime collection
    static LinkedList<Anime> animeList = new LinkedList<>();

    // CO3: Stack used for Recently Viewed Anime (LIFO principle)
    static Stack<Anime> watchHistory = new Stack<>();

    // CO3: Queue used for Trending Anime (FIFO principle)
    static Queue<Anime> trendingQueue = new LinkedList<>();

    // CO4: HashMap used for fast searching using hashing technique
    static HashMap<String, Anime> animeMap = new HashMap<>();


    // Add Anime
    // CO2: Insert operation in List ADT
    // CO4: Store data in HashMap for efficient lookup
    static void addAnime(String title, String genre, String status) {

        Anime a = new Anime(title, genre, status);

        animeList.add(a);          // CO2: Linked List insertion
        animeMap.put(title.toLowerCase(), a); // CO4: Hashing
        trendingQueue.add(a);     // CO3: Queue insertion
    }


    // Display Anime
    // CO2: Traversal operation in Linked List
    // CO5: Practical application of displaying stored data
    static void displayAnime() {

        if(animeList.isEmpty()) {
            System.out.println("No anime available.");
            return;
        }

        for(Anime a : animeList) {
            a.display();
        }
    }


    // Linear Search
    // CO1: Implementation of Linear Search algorithm
    // Time Complexity: O(n)
    static void linearSearch(String key) {

        boolean found = false;

        for(Anime a : animeList) {

            if(a.title.toLowerCase().contains(key.toLowerCase())) {

                if(!found)
                    System.out.println("Matching Anime:");

                a.display();

                watchHistory.push(a); // CO3: Stack operation
                found = true;
            }
        }

        if(!found)
            System.out.println("No matching anime found.");
    }


    // Hash Search
    // CO4: Searching using HashMap (Hash Table)
    // Average Time Complexity: O(1)
    static void hashSearch(String key) {
        boolean found = false;
        for(String title : animeMap.keySet()) {
            if(title.contains(key.toLowerCase())) {
                Anime a = animeMap.get(title);
                if(!found)
                    System.out.println("Matching Anime (Hash Search):");
                a.display();
                watchHistory.push(a); // CO3: Stack usage
                found = true;
            }
        }
        if(!found)
            System.out.println("No matching anime found.");
    }


    // Bubble Sort
    // CO1: Sorting algorithm implementation
    // Time Complexity: O(n²)
    static void bubbleSort() {
        int n = animeList.size();
        for(int i=0;i<n-1;i++) {
            for(int j=0;j<n-i-1;j++) {
                Anime a1 = animeList.get(j);
                Anime a2 = animeList.get(j+1);
                if(a1.title.compareToIgnoreCase(a2.title) > 0) {
                    animeList.set(j, a2);
                    animeList.set(j+1, a1);
                }
            }
        }

        System.out.println("Anime Sorted using Bubble Sort.");
    }


    // Binary Search
    // CO1: Efficient searching algorithm
    // Time Complexity: O(log n) after sorting
    static void binarySearch(String key) {

        bubbleSort(); // Sorting required before binary search

        int left = 0;
        int right = animeList.size()-1;

        while(left <= right) {

            int mid = (left + right) / 2;

            Anime a = animeList.get(mid);

            int cmp = a.title.compareToIgnoreCase(key);

            if(cmp == 0) {

                System.out.println("Anime Found (Binary Search)");
                a.display();

                watchHistory.push(a); // CO3: Stack usage
                return;
            }

            if(cmp < 0)
                left = mid + 1;
            else
                right = mid - 1;
        }

        // If exact match not found
        System.out.println("Exact match not found. Showing similar anime:");

        boolean found = false;

        for(Anime a : animeList) {

            if(a.title.toLowerCase().contains(key.toLowerCase())) {

                a.display();

                watchHistory.push(a); // CO3
                found = true;
            }
        }

        if(!found)
            System.out.println("No similar anime found.");
    }


    // Show Trending Anime
    // CO3: Queue traversal (FIFO structure)
    static void showTrending() {

        System.out.println("Trending Anime:");

        for(Anime a : trendingQueue) {
            System.out.println(a.title);
        }
    }


    // Circular Queue Rotation (Slider Effect)
    // CO3: Queue rotation simulating circular queue behavior
    static void rotateTrending() {

        Anime first = trendingQueue.poll();

        if(first != null)
            trendingQueue.add(first);

        System.out.println("Trending slider rotated.");
    }


    // Recently Viewed (Stack)
    // CO3: Stack traversal (LIFO)
    static void showHistory() {
        if(watchHistory.isEmpty()) {
            System.out.println("No recently viewed anime.");
            return;
        }
        System.out.println("Recently Viewed:");
        Stack<Anime> temp = (Stack<Anime>) watchHistory.clone();
        while(!temp.isEmpty()) {
            System.out.println(temp.pop().title);
        }
    }


    // CO5 & CO6:
    // Complete practical application integrating multiple DSA concepts
    // Demonstrates real-world system design using searching, sorting,
    // lists, stacks, queues, and hashing.
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Initial anime dataset
        addAnime("Jujutsu Kaisen","Action","Ongoing");
        addAnime("One Piece","Adventure","Ongoing");
        addAnime("Demon Slayer","Action","Ongoing");
        addAnime("Attack on Titan","Action","Completed");
        addAnime("Naruto","Adventure","Completed");
        addAnime("Solo Leveling","Fantasy","Ongoing");
        addAnime("Gachiakuta","Action","Ongoing");


        while(true) {

            System.out.println("\n========== FYNAT Anime Discovery System ==========");
            System.out.println("1. View All Anime");
            System.out.println("2. Search Anime (Linear Search)");
            System.out.println("3. Quick Search Anime (Hash Search)");
            System.out.println("4. Sort Anime Titles (Bubble Sort)");
            System.out.println("5. Search Anime (Binary Search)");
            System.out.println("6. View Trending Anime");
            System.out.println("7. Rotate Trending Slider");
            System.out.println("8. View Recently Watched Anime");
            System.out.println("9. Add New Anime");
            System.out.println("10. Exit System");

            System.out.print("\nEnter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch(choice) {

                case 1:
                    displayAnime();
                    break;

                case 2:
                    System.out.print("Enter anime title to search: ");
                    linearSearch(sc.nextLine());
                    break;

                case 3:
                    System.out.print("Enter anime title to search: ");
                    hashSearch(sc.nextLine());
                    break;

                case 4:
                    bubbleSort();
                    break;

                case 5:
                    System.out.print("Enter anime title to search: ");
                    binarySearch(sc.nextLine());
                    break;

                case 6:
                    showTrending();
                    break;

                case 7:
                    rotateTrending();
                    break;

                case 8:
                    showHistory();
                    break;

                case 9:

                    System.out.print("Enter Anime Title: ");
                    String title = sc.nextLine();

                    System.out.print("Enter Genre: ");
                    String genre = sc.nextLine();

                    System.out.print("Enter Status (Ongoing/Completed): ");
                    String status = sc.nextLine();

                    addAnime(title, genre, status);

                    System.out.println("Anime added successfully!");
                    break;

                case 10:
                    System.out.println("\nThank you for using FYNAT Anime Discovery System!");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}