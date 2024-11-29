-- Categories
INSERT INTO categories (name) VALUES
('Programming Languages'),
('Web Development'),
('Mobile Development'),
('DevOps'),
('Database'),
('Machine Learning'),
('Cloud Computing');

-- Topics
INSERT INTO topics (name) VALUES
('Python'),
('JavaScript'),
('Java'),
('React'),
('Node.js'),
('Docker'),
('AWS'),
('SQL'),
('Git'),
('Kubernetes'),
('Go'),
('Vue.js'),
('Angular'),
('Spring'),
('Jenkins'),
('Google Cloud Platform'),
('Azure'),
('MongoDB'),
('PostgreSQL'),
('Redis'),
('Visual Studio Code'),
('IntelliJ IDEA'),
('Android'),
('iOS'),
('Flutter'),
('TensorFlow'),
('PyTorch'),
('Scikit-learn');

-- Users (password is 'password123' - you should change this in production)
INSERT INTO users (email, username, password, created_at) VALUES
('admin@example.com', 'admin', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', NOW()),
('user1@example.com', 'user1', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', NOW()),
('user2@example.com', 'user2', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', NOW());

-- Resources
INSERT INTO resources (name, url, description, image_url, status, created_at, updated_at) VALUES
-- Programming Language Resources
('Python Official Documentation', 'https://docs.python.org', 'The official Python programming language documentation with comprehensive guides and tutorials', 'https://www.python.org/static/img/python-logo.png', 'Publish', NOW(), NOW()),
('JavaScript MDN Web Docs', 'https://developer.mozilla.org/en-US/docs/Web/JavaScript', 'Complete JavaScript documentation and tutorials from Mozilla', 'https://developer.mozilla.org/mdn-social-share.cd6c4a5a.png', 'Publish', NOW(), NOW()),
('Java SE Documentation', 'https://docs.oracle.com/javase/tutorial/', 'Official Java SE programming tutorials and documentation', 'https://www.oracle.com/a/tech/img/java-logo.png', 'Publish', NOW(), NOW()),
('Go Documentation', 'https://golang.org/doc/', 'Official Go programming language documentation and tutorials', 'https://golang.org/lib/godoc/images/go-logo-blue.svg', 'Publish', NOW(), NOW()),

-- Web Development Frameworks
('React Documentation', 'https://reactjs.org', 'A JavaScript library for building modern user interfaces', 'https://reactjs.org/logo-og.png', 'Publish', NOW(), NOW()),
('Vue.js Guide', 'https://vuejs.org/guide/introduction.html', 'Progressive JavaScript framework for building user interfaces', 'https://vuejs.org/images/logo.png', 'Publish', NOW(), NOW()),
('Angular Documentation', 'https://angular.io/docs', 'Platform for building mobile and desktop web applications', 'https://angular.io/assets/images/logos/angular/angular.png', 'Publish', NOW(), NOW()),
('Spring Framework', 'https://spring.io/guides', 'Building production-ready applications with Spring', 'https://spring.io/images/spring-logo-9146a4d3298760c2e7e49595184e1975.svg', 'Publish', NOW(), NOW()),

-- DevOps Tools
('Docker Documentation', 'https://docs.docker.com', 'Learn container technology and Docker platform', 'https://www.docker.com/sites/default/files/d8/2019-07/horizontal-logo-monochromatic-white.png', 'Publish', NOW(), NOW()),
('Kubernetes Docs', 'https://kubernetes.io/docs/home/', 'Production-grade container orchestration', 'https://kubernetes.io/images/favicon.png', 'Publish', NOW(), NOW()),
('Jenkins Handbook', 'https://www.jenkins.io/doc/', 'Automation server for building, deploying, and automating projects', 'https://www.jenkins.io/images/logo-title-opengraph.png', 'Publish', NOW(), NOW()),

-- Cloud Platforms
('AWS Documentation', 'https://docs.aws.amazon.com', 'Amazon Web Services cloud platform documentation', 'https://a0.awsstatic.com/libra-css/images/logos/aws_logo_smile_1200x630.png', 'Publish', NOW(), NOW()),
('Google Cloud Platform', 'https://cloud.google.com/docs', 'GCP documentation and tutorials', 'https://cloud.google.com/images/social-icon-google-cloud-1200-630.png', 'Publish', NOW(), NOW()),
('Azure Documentation', 'https://docs.microsoft.com/azure/', 'Microsoft Azure cloud platform resources', 'https://docs.microsoft.com/azure/media/index/azure-logo.png', 'Publish', NOW(), NOW()),

-- Database Technologies
('MongoDB Manual', 'https://docs.mongodb.com/manual/', 'NoSQL database documentation and tutorials', 'https://webassets.mongodb.com/_com_assets/cms/mongodb_logo1-76twgcu2dm.png', 'Publish', NOW(), NOW()),
('PostgreSQL Documentation', 'https://www.postgresql.org/docs/', 'Advanced open source relational database', 'https://www.postgresql.org/media/img/about/press/elephant.png', 'Publish', NOW(), NOW()),
('Redis Documentation', 'https://redis.io/documentation', 'In-memory data structure store documentation', 'https://redis.io/images/redis-white.png', 'Publish', NOW(), NOW()),

-- Development Tools
('Git Documentation', 'https://git-scm.com/doc', 'Distributed version control system documentation', 'https://git-scm.com/images/logos/downloads/Git-Logo-2Color.png', 'Publish', NOW(), NOW()),
('Visual Studio Code', 'https://code.visualstudio.com/docs', 'Code editing. Redefined.', 'https://code.visualstudio.com/assets/images/code-stable.png', 'Publish', NOW(), NOW()),
('IntelliJ IDEA Help', 'https://www.jetbrains.com/help/idea/', 'Capable and Ergonomic IDE for JVM', 'https://resources.jetbrains.com/storage/products/intellij-idea/img/meta/intellij-idea_logo_300x300.png', 'Publish', NOW(), NOW()),

-- Mobile Development
('Android Developers', 'https://developer.android.com/docs', 'Official Android development documentation', 'https://developer.android.com/images/brand/Android_Robot.png', 'Publish', NOW(), NOW()),
('iOS Developer', 'https://developer.apple.com/documentation/', 'Official Apple iOS development resources', 'https://developer.apple.com/assets/elements/icons/xcode/xcode-96x96.png', 'Publish', NOW(), NOW()),
('Flutter Documentation', 'https://flutter.dev/docs', 'UI toolkit for building natively compiled applications', 'https://flutter.dev/images/flutter-logo-sharing.png', 'Publish', NOW(), NOW()),

-- AI and Machine Learning
('TensorFlow Guide', 'https://www.tensorflow.org/guide', 'Open source machine learning platform', 'https://www.tensorflow.org/images/tf_logo_social.png', 'Publish', NOW(), NOW()),
('PyTorch Tutorials', 'https://pytorch.org/tutorials/', 'Open source machine learning library', 'https://pytorch.org/assets/images/pytorch-logo.png', 'Publish', NOW(), NOW()),
('Scikit-learn Documentation', 'https://scikit-learn.org/stable/', 'Machine learning in Python', 'https://scikit-learn.org/stable/_static/scikit-learn-logo-small.png', 'Publish', NOW(), NOW());

-- Resource Categories
INSERT INTO resources_categories (resource_id, category_id) VALUES
(1, 1), -- Python docs in Programming Languages
(2, 2), -- JavaScript MDN in Web Development
(3, 1), -- Java SE Documentation in Programming Languages
(4, 1), -- Go Documentation in Programming Languages
(5, 2), -- React docs in Web Development
(6, 2), -- Vue.js Guide in Web Development
(7, 2), -- Angular Documentation in Web Development
(8, 2), -- Spring Framework in Web Development
(9, 4), -- Docker in DevOps
(10, 4), -- Kubernetes Docs in DevOps
(11, 4), -- Jenkins Handbook in DevOps
(12, 7), -- AWS in Cloud Computing
(13, 7), -- Google Cloud Platform in Cloud Computing
(14, 7), -- Azure Documentation in Cloud Computing
(15, 5), -- MongoDB Manual in Database
(16, 5), -- PostgreSQL Documentation in Database
(17, 5), -- Redis Documentation in Database
(18, 6), -- Git Documentation in Development Tools
(19, 6), -- Visual Studio Code in Development Tools
(20, 6), -- IntelliJ IDEA Help in Development Tools
(21, 3), -- Android Developers in Mobile Development
(22, 3), -- iOS Developer in Mobile Development
(23, 3), -- Flutter Documentation in Mobile Development
(24, 6), -- TensorFlow Guide in AI and Machine Learning
(25, 6), -- PyTorch Tutorials in AI and Machine Learning
(26, 6); -- Scikit-learn Documentation in AI and Machine Learning

-- Resource Topics
INSERT INTO resources_topics (resource_id, topic_id) VALUES
(1, 1), -- Python docs with Python topic
(2, 2), -- JavaScript MDN with JavaScript topic
(3, 3), -- Java SE Documentation with Java topic
(4, 11), -- Go Documentation with Go topic
(5, 4), -- React docs with React topic
(6, 12), -- Vue.js Guide with Vue.js topic
(7, 13), -- Angular Documentation with Angular topic
(8, 14), -- Spring Framework with Spring topic
(9, 6), -- Docker docs with Docker topic
(10, 10), -- Kubernetes Docs with Kubernetes topic
(11, 15), -- Jenkins Handbook with Jenkins topic
(12, 7), -- AWS docs with AWS topic
(13, 16), -- Google Cloud Platform with Google Cloud Platform topic
(14, 17), -- Azure Documentation with Azure topic
(15, 18), -- MongoDB Manual with MongoDB topic
(16, 19), -- PostgreSQL Documentation with PostgreSQL topic
(17, 20), -- Redis Documentation with Redis topic
(18, 9), -- Git Documentation with Git topic
(19, 21), -- Visual Studio Code with Visual Studio Code topic
(20, 22), -- IntelliJ IDEA Help with IntelliJ IDEA topic
(21, 23), -- Android Developers with Android topic
(22, 24), -- iOS Developer with iOS topic
(23, 25), -- Flutter Documentation with Flutter topic
(24, 26), -- TensorFlow Guide with TensorFlow topic
(25, 27), -- PyTorch Tutorials with PyTorch topic
(26, 28); -- Scikit-learn Documentation with Scikit-learn topic