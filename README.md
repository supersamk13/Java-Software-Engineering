# Java-Software-Engineering
Software engineering and development projects I have worked on in Java. 

Project: Web Crawler
Project Summary:

The project is a Java application called "Web Page Scanner and Image Viewer." It consists of two main components: a web page scanner (PageScanner) and an image viewer (PictureViewer). The web page scanner is designed to explore web pages, extract links, and launch new threads to scan linked pages. The image viewer displays images found during the scanning process.

### Key Features:

### Web Page Scanner (PageScanner):
Scans a web page for both links and images.
Launches new threads to explore linked pages, creating a multithreaded web crawling mechanism.
Utilizes regular expressions to extract legitimate HTML links and image tags.

### Image Viewer (PictureViewer):
Displays images from URLs on a graphical user interface (GUI).
Handles errors gracefully and prints informative messages in case of exceptions.
Provides functionality to scale and display images while meeting minimum size requirements.

### Thread Management:
Controls the number of active threads to prevent CPU overload.
Uses synchronization to ensure that duplicate links and images are not processed.

### User Interface:
Utilizes Java Swing for the GUI, allowing users to view images in a resizable frame.
Displays image names and handles URL encoding for proper image retrieval.

### Data Structures and Algorithms:
Implements data structures such as HashSet for efficient storage and retrieval of processed links and images.
Utilizes multithreading algorithms for effective web crawling and exploration.
Overall, this project offers a robust and scalable solution for web page scanning and image viewing, showcasing effective multithreading, data structures, and algorithmic techniques in Java.
