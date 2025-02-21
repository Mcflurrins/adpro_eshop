
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
  <summary> Module 2 </summary>

 ### Reflection 1 
 1. I fixed one of the code quality fixes highlighted by Sonarcloud by omitting the use of 'public' keyword in the test files within the controller directory. According to SonarCloud, this was a code quality issue because JUnit 5 no longer requires the use of the 'public' keyword. This change has been implemented since the JUnit 5 default access modifier has been changed to package-private. In a stack overflow forum I found, Sam Branner, part of the JUnit 5 maintanence team says this is because the team believes in the principle of "less is more" when it comes to coding. Apart from that, I also added an assertion to the test in EshopApplicationTests.java as recommended by SonarCloud.

 2. The CI/CD workflows I've implemented has met the defintion of Continuous Integration and Continuous Deployment. With the github workflow scripts in my code, when a push happens, the code is immediately tested and analyzed for code smells (continuous integration). I have also put my repository on Koyeb and set it to auto-deploy (continuous  deployment). However, this was done without a script on my github workflows directory. This is due to the limitation of Koyeb's free plan, as discussed in the helpdesk of the AdvProg discord. 

</details>
