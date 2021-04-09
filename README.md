# better-every-day

# Initializing project Windows
1. create workspace with java 8(javaSE-1.8)
2. file > import > git > projects from git (with smart import) > clone uri
3. set the local destination directory to workspace
4. go to help > eclipse marketplace
5. make sure e(fx)clipse 3.7.0, EGit, and Maven Integration for Eclipse are installed (git and maven should already be installed)
6. download javafx sdk version 16(latest) [https://gluonhq.com/products/javafx/]
7. unzip and move to workspace directory
8. right click on project > build path > configure build path > libraries
9. select all javafx .jar s and remove them (if there is an error)
10. add external jars > go to sdk > lib > select all .jar
11. apply
12. click on JRE System Library(JavaSE-1.8) > edit > change environment to java 11 > finish
13. apply
14. move all .jar to modulepath
15. apply
16. change jre system library back to 1.8 > apply and close
17. open Main.java in project
18. try and run (it shouldn't work)
19. go to run configurations(dropdown next to run button)
20. the main class should be application.Main
21. go to dependencies > Add mocules (at bottom) > ALL-MODULE-PATH > apply
22. Now it should work!
There's prob an easier way to do it ¯\_(ツ)_/¯

# Git
## Pulling and Branching
Its a good idea to branch whenever working on a new thing so there is no conflict
1. Window > Show View > Git > Git Repositories
2. right click on better-every-day
3. Switch To > for branching
4. or Pull

## Commiting
Make sure you are working with the latest version before commiting.
1. Window > Show View > Git(might be in other) > Git Staging
2. See changes in Unstaged changes
3. Add to staged with green plus
4. Add commit message
5. Commit and push

## Merging
When you're done on your branch and want to merge to master
1. Go to PUll requests on GitHub
2. New Pull Request
3. Choose compare:<your-branch>
4. Create Pull Request
We can see changes and resolve any conflicts before merging
