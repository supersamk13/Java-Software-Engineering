# Java-Software-Engineering
Software engineering and development projects I have worked on in Java. 

## Project: Web Crawler

### Project Summary:
The project is a Java application called "Web Page Scanner and Image Viewer." It consists of two main components: a web page scanner (PageScanner) and an image viewer (PictureViewer). The web page scanner is designed to explore web pages, extract links, and launch new threads to scan linked pages. The image viewer displays images found during the scanning process. The project is run via the Driver. 

### Key Features:

#### Web Page Scanner (PageScanner):
Scans a web page for both links and images.
Launches new threads to explore linked pages, creating a multithreaded web crawling mechanism.
Utilizes regular expressions to extract legitimate HTML links and image tags.

#### Image Viewer (PictureViewer):
Displays images from URLs on a graphical user interface (GUI).
Handles errors gracefully and prints informative messages in case of exceptions.
Provides functionality to scale and display images while meeting minimum size requirements.

#### Thread Management:
Controls the number of active threads to prevent CPU overload.
Uses synchronization to ensure that duplicate links and images are not processed.

#### User Interface:
Utilizes Java Swing for the GUI, allowing users to view images in a resizable frame.
Displays image names and handles URL encoding for proper image retrieval.

#### Data Structures and Algorithms:
Implements data structures such as HashSet for efficient storage and retrieval of processed links and images.
Utilizes multithreading algorithms for effective web crawling and exploration.
Overall, this project offers a robust and scalable solution for web page scanning and image viewing, showcasing effective multithreading, data structures, and algorithmic techniques in Java.


## Project: Binary Search Tree (BST) Implementation in Java

### Project Summary
This project is a custom implementation of a Binary Search Tree (BST) in Java, designed with special functionalities suitable for various projects. The BST class supports key-value pairs, where keys are comparable, and the tree maintains the ordering property.

### Key Features:

#### Search and Retrieval:
Efficient key-based search using the get method.
Reverse lookup capability to find keys based on their associated values.

#### Tree Analysis:
Counting the number of leaves in the tree (countLeaves method).
Retrieving the value associated with the smallest key (getValueForSmallestKey method).
Determining the height of the tree (getHeight method).
Checking if the tree is balanced (isBalanced method).

#### Modification and Removal:
Inserting new key-value pairs (put method) with support for updating existing keys.
Removing nodes based on keys (remove method).
Clearing the entire tree (clear method).

#### Utility Functions:
String representation of the tree using the toString method.
Checking for key or value existence (containsKey, containsValue methods).

### Usage:
#### Integration:
Easily integrate the BinarySearchTree class into projects requiring efficient key-based data storage and retrieval.

#### Testing:
Comprehensive JUnit test suite (PublicTests class) covering various aspects of the BST functionality.

#### Demonstration:
Demonstrate usage through provided examples of creating small and medium-sized trees.

### Testing Approach:
Well-structured JUnit test cases for each method, ensuring the correctness and robustness of the BST implementation.
Tests cover scenarios such as searching, counting leaves, inserting, updating, removing, and analyzing tree properties.

### Future Development:
Continuous improvement and optimization of the BST implementation.
Addition of more advanced functionalities and utility methods.

### How to Use:

#### Integration:
Simply import the BinarySearchTree class into your Java project.

#### Testing:
Utilize the provided JUnit test suite (PublicTests class) to validate the correctness of the BST implementation.

#### Customization:
Extend the functionalities based on specific project requirements.

Feel free to explore and contribute to the project!
