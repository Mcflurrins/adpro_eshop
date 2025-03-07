
# Flori Andrea Ng - 2306171713 - KKI
<details>
<summary> 🔖 Module 1 </summary>
  
### 📌 Reflection 1

#### You already implemented two new features using Spring Boot. Check again your source code and evaluate the coding standards that you have learned in this module. Write clean code principles and secure coding practices that have been applied to your code.  If you find any mistake in your source code, please explain how to improve your code. Please write your reflection inside the repository's README.md file.

The coding standards that I have learned and implemented in this exercise are readability, reliability and reusability. By using a UUID for product id's during product creation, the program becomes more reliable and secure because the id's are unique and not simply enumerated by incrementing the value. This also helps the product become more reusable when implementing editing and deletion because we can simply fetch the UUID of the product that we want to edit/delete. I also did my best to make the code readable by putting simple, descriptive names for the variables. In addition, I keep the formatting consistent with the same amount of coding style and indentation. The functions are also kept short and concise so that they only focus on a single task. 

### 📌 Reflection 2

#### After writing the unit test, how do you feel? How many unit tests should be made in a class? How to make sure that our unit tests are enough to verify our program? It would be good if you learned about code coverage. Code coverage is a metric that can help you understand how much of your source is tested. If you have 100% code coverage, does that mean your code has no bugs or errors?

After writing the unit test, I feel relieved because I put in a lot of work into configuring how they work. We can make sure that our unit tests are enough to verify our program by writing separate unit cases for the failures and successes of each of our functions. To help with this, we can use certain tools to receive a metric called code coverage, which helps to check what percentage of lines in our program is covered by our unit tests. For example, a 50% code coverage means that 50% of the lines in our program are covered by our unit tests. Even if we have 100% code coverage for example, our code may still have bugs and errors. Code coverage only measures the percentage of code that is executed by tests, but it does not check whether the tests can catch all the possible issues with our code. It also doesn't help in measuring the quality of our code, plus logic errors and edge cases might still go unnoticed.

#### Suppose that after writing the CreateProductFunctionalTest.java along with the corresponding test case, you were asked to create another functional test suite that verifies the number of items in the product list. You decided to create a new Java class similar to the prior functional test suites with the same setup procedures and instance variables. What do you think about the cleanliness of the code of the new functional test suite? Will the new code reduce the code quality? Identify the potential clean code issues, explain the reasons, and suggest possible improvements to make the code cleaner! Please write your reflection inside the repository's README.md file.

If I made a new functional test suite in addition to CreateProductFunctionalTest.java, where there would be a lot of code duplication in terms of setup procedures and initializing variables, then it would dirty the code. This is because it would go against clean coding principles, specifically in the DRY rule (Don't Repeat Yourself). The maintenance of such code would be a lot more time-consuming and it would reduce the code quality. For example, if I wanted to change the instance variables, I'd have to implement the changes in both the test suites. To avoid repetition, we could instead make a base test class for the common set-up procedures. Our product-creation test suite and item-counting test suite could then extend the base test class so they inherit the same set-up and instance variables. 
</details>

<details>
  <summary> 🔖 Module 2 </summary>

 ### 📌 Reflection 1 
 1. I fixed one of the code quality fixes highlighted by Sonarcloud by omitting the use of 'public' keyword in the test files within the controller directory. According to SonarCloud, this was a code quality issue because JUnit 5 no longer requires the use of the 'public' keyword. This change has been implemented since the JUnit 5 default access modifier has been changed to package-private. In a stack overflow forum I found, Sam Branner, part of the JUnit 5 maintanence team says this is because the team believes in the principle of "less is more" when it comes to coding. Apart from that, I also added an assertion to the test in EshopApplicationTests.java as recommended by SonarCloud.

 2. The CI/CD workflows I've implemented has met the defintion of Continuous Integration and Continuous Deployment. With the github workflow scripts in my code, when a push happens, the code is immediately tested and analyzed for code smells (continuous integration). I have also put my repository on Koyeb and set it to auto-deploy (continuous  deployment). However, this was done without a script on my github workflows directory. This is due to the limitation of Koyeb's free plan, as discussed in the helpdesk of the AdvProg discord.

Note: The koyeb deployment link is attached to the github repo below the description :D

</details>

<details>
  <summary> 🔖 Module 3 </summary>

 ### 📌 Reflection 1 
 #### Explain what principles you apply to your project!

The SOLID principles I applied in my project are: 
#### SRP: Single Responsibility Principle
The Single Responsibility Principle means that each Java Class should have only one function. Now, in the pre-existing base code I noticed that the ProductController class was not only acting as a controller for the Product Class, but also as a parent to the CarController class which extends it. To fix this, I made the CarController class its own independent class and put it in a separate module. 

#### OCP: Open Closed Principle
The Open-Closed Principle means a software artifact should be open for extension but closed for modification. For example, in the service folder, we initially had a CarService interface and a ProductService interface. 
They both essentially had the same functionality but for handling different object classes, so I made a new GeneralService Interface which covers both their methods. This makes the interface open for extension, e.g. if we want to make a similar third service, like MotorService.
I also implemented this by making a CarRepositoryInterface and a ProductRepositoryInterface. Now, if we need to change the implementation of these repositories, we can extend a new class instead of modifying CarRepository and ProductRepository directly.

#### LSP: Liskov Substitution Principle
The Liskov Substitution Principle says: "Derived or child classes must be substitutable for their base or parent classes." When I made that GeneralService Interface earlier, a ton of errors popped up because it turns out that
even though CarService and ProductService had the same find, edit and delete functionality, the way they were each implemented was inconsistent. ProductService's update function was called 'edit' and returned an object, while CarService's was called 'update' and returned nothing. 
ProductService's deletion function was called 'delete', while in CarService it was 'deleteByCarId'. In ProductService, the finding function returned an Optional Product, while in CarService it returned only Car. 
These inconsistencies mean that the child classes can't substitute the new parent class GeneralService, so I altered the find, edit and delete functionality in order to accomodate for that, making minor changes to Product and Car's Repositories and Controllers. For the same reason, I made CarRepositoryInterface and a ProductRepositoryInterface extend a GeneralRepositoryInterface.

#### DIP: Dependency Inversion Principle
The Dependency Inversion Principle suggests that high-level modules should not rely on low-level modules directly, and instead, both should communicate through an abstraction. 

The CarServiceImpl and ProductServiceImpl directly depended on concrete repository classes, like this for example:
```
@Autowired
private CarRepository carRepository;
```
but now, they depend on the abstract repository interface, like this:
```
@Autowired
private CarRepositoryInterface carRepository;
```
This allows us to make changes to the implementation (the repository) without changing the service layer. :)

 ### 📌 Reflection 2
 #### Explain the advantages of applying SOLID principles to your project with examples.
1. Better Organization: For example, separating CarController into its own class and module helps with improving code readability and also keeps it maintainable. When we add too many features and functionality into one class, it results in lengthy and complex code which is a burden to modify later on. These smaller and better-structured classes are easier to navigate too! :D
2. Prevents Introduction of New Bugs: The implementation of the repository interfaces allows me to extend the behavior of, say, CarRepository, without having to change it directly. The act of extending instead of directly modifying existing code will help prevent the introduction of new bugs into an already functioning application.
3. Enhances Flexibility: The implementation of the repository interfaces (CarRepositoryInterface and ProductRepositoryInterface) allows us to make changes to it without having to also modify the service layer - they are abstractions which enhance flexibility in coding and separates concerns between high-level and low-level modules. 

 ### 📌 Reflection 3
 #### Explain the advantages of applying SOLID principles to your project with examples.
1. Inconsistent code: Without applying SOLID principles, code can easily get repetitive and inconsistent. For example, the CarService and ProductService used to have basically the same CRUD functionality, but they would each return different objects and have differing function names. This might result in poor readability and slow down maintenance.
2. Difficult to add new features: Now that I have implemented a generic GeneralService interface, if I wanted to make a third interface like OrderService, then I could simply extend it from the GeneralService interface. However, if this had not been done, making a third interface would be a lot more cumbersome and full of repetitive code.
3. Tightly Coupled Code: Without separate interfaces, ProductService might directly depend on CarService, making modifications to one class affect the other. This increases the risk of unintended side effects.

</details>

<details>
<summary>
🔖 Module 4
</summary>

#### Do I have enough functional tests to reassure myself that my application really works, from the point of view of the user?
I believe my current tests cover many of the critical functionalities that a user would rely on, like order creation, status updates, and the correctness of creating orders and payments. However, while unit tests ensure that individual components behave as expected, they don’t always capture the full picture of the user experience, so maybe moving fforward I could add some functional tests to really simulate an end-to-end experience of using the app.

#### Am I testing all the edge cases thoroughly?
My tests already cover a good range of edge cases, including invalid inputs, empty product lists, and inappropriate status values, which helps in catching many potential bugs. That said, software rarely behaves perfectly in every unforeseen situation, so there might be additional edge cases that I haven’t yet addressed.

#### Do I have tests that check whether all my components fit together properly? Could some integrated tests do this, or are functional tests enough?
While my unit tests check that each component works well on its own, they don't really guarantee that components will integrate seamlessly. A set of integration tests, which simulate real interactions between components, can catch issues that only appear when the whole system is used. Therefore, even if functional tests provide a solid baseline, also adding integration tests would give me more confidence that everything works together correctly.

#### Are my tests giving me the confidence to refactor my code, fearlessly and frequently?
Yes, the tests are like a safety net that enables me to refactor my code without worrying too much about breaking existing functionality. When I make changes or improvements, the tests quickly alert me for any bugs so I can code in peace.

#### Are my tests helping me to drive out a good design? If I have a lot of integration tests but less unit tests, do I need to make more unit tests to get better feedback on my code design?
The tests I’ve written so far have helped in developing good design by enforcing clear boundaries between components. While integration tests help ensure that the overall system works, unit tests are more effective at pinpointing issues in specific modules, giving more feedback on code design per the module being tested.

#### Are my feedback cycles as fast as I would like them? When do I get warned about bugs, and is there any practical way to make that happen sooner?
Yeah,my unit tests run really fast and give me feedback as I develop. However, the integration tests can take longer to execute, which might slow down the overall feedback cycle. To mitigate this, the fast unit tests are run locally, while the integration tests run in the continuous integration pipeline, as implemented in module 2. This way, I’m warned about bugs immediately during development and still get comprehensive checks later.

#### Is there some way that I could write faster integration tests that would get me feedback quicker?
To speed up my integration tests, I could look into using in-memory databases or containerized services that mimic the production environment. Additionally, optimizing the test data and testing only the critical user flows can help reduce execution time.

#### Can I run a subset of the full test suite when I need to?
Yes, I can run only a subset of the full test suite, which is why there's multiple folders (like model, repository, service, functional, etc.) in the test directory, so that I can conveniently test only a subset of the full test suite.

#### Am I spending too much time waiting for tests to run, and thus less time in a productive flow state?
Not really, I think my tests run pretty quick, so I can continue working on my productive implementations with consistent feedback.

| F.I.R.S.T Principle | Reflection & Next Steps                                                                                                                                                          |
|---------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Fast** | Most of my unit tests run quickly, which is great, but some integration tests slow things down. I can mitigate this by running smaller test subsets locally for faster feedback. |
| **Independent** | My tests are mostly independent. Moving forward, I need to stay mindful of hidden dependencies and refactor if any tests start relying on shared state.                          |
| **Repeatable** | The tests give consistent results across local and CI environments, which is a good sign. To keep it that way, I need to ensure external dependencies are mocked properly.       |
| **Self-Validating** | The tests provide clear pass/fail feedback, making debugging easier. I should continue writing strong assertions and detailed failure messages for better clarity.               |
| **Timely** | Writing tests alongside development helped catch issues early. Moving forward, this helps me code productively faster.                                                           |


</details>

