//Lets code!
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Dependencies {
	//Here we have a method that checks whether there is such folder with an example name
	public static boolean checkInFolder(File file, String packageToCheck) throws FileNotFoundException{
		//an array full of names 
		String[] directories = file.list();
		for (int i = 0; i < directories.length; i++) {
			if(directories[i].contains(packageToCheck)){
				return true;
			}
		}
		return false;
	}
	//end of checkInFolder method
	
	//Now we have resolveDependencies method which takes the package file and installed the needed module:
	public static void resolveDependencies(File file, String startPackage,File modules) throws FileNotFoundException{
		//First we check whether there is an already installed module with startPackage name
		if (checkInFolder(modules, startPackage)) {
			System.out.println(startPackage+" is already installed");
		}
		else{
			@SuppressWarnings("resource")
			Scanner fileReader = new Scanner(file); //Start reading
			String line;
			while (fileReader.hasNextLine()) {
				line = fileReader.nextLine();
				if (line.contains(startPackage)&&(line.substring(line.indexOf('['),line.lastIndexOf(']')).contains(startPackage)==false)) {
					//if the startPackage doesn't need other modules to be installed
					if (line.indexOf('[')+1==line.indexOf(']')) {
							System.out.println("Installing "+startPackage);
							modules=new File("D:\\HackBulgaria\\Packages\\installed_modules\\"+startPackage);
							modules.mkdir();
							modules=new File("D:\\HackBulgaria\\Packages\\installed_modules\\");
						return;
					}
					//if we need to install many modules to get startPackage installed
					else if(line.substring(line.indexOf('['),line.lastIndexOf(']')).contains(",")){
						String[] words =line.substring(line.indexOf('[')+2,line.lastIndexOf(']')-1).split("[ ,\"]+");
						System.out.println("Installing "+startPackage);
						//Creating a folder with that name
						modules=new File("D:\\HackBulgaria\\Packages\\installed_modules\\"+startPackage);
						modules.mkdir();
						modules=new File("D:\\HackBulgaria\\Packages\\installed_modules\\");
						System.out.print("In order to install "+startPackage+" we need ");
						for (int i=0;i<words.length;i++){
							if (i==words.length-1) {
								System.out.println("and "+words[i]);
							} else {
								System.out.print(words[i]+" ");	
							}
						}
						
						for (int i=0;i<words.length;i++) {	
							resolveDependencies(file, words[i],modules);
						}
						return;
					}
					//else we need to install a single module
					else{
						System.out.println("Installing "+startPackage);
						System.out.println("In order to install "+startPackage+" we need "+line.substring(line.indexOf('[')+2,line.lastIndexOf(']')-1));
						resolveDependencies(file, line.substring(line.indexOf('[')+2,line.lastIndexOf(']')-1),modules);
						modules=new File("D:\\HackBulgaria\\Packages\\installed_modules\\"+startPackage);
						modules.mkdir();
						modules=new File("D:\\HackBulgaria\\Packages\\installed_modules\\");
						return;
					}
				}
			}
			fileReader.close(); //Close
		}
	}
	
	//Here is a method that returns the name of the main module we need to install
	public static String readDependencies(File fileWeCheck, String moduleWeNeed) throws FileNotFoundException{
		Scanner fileReader= new Scanner(fileWeCheck);
		String line=null;
		while (fileReader.hasNextLine()) {
			line = fileReader.nextLine();
			if(line.contains(moduleWeNeed)){
				fileReader.close();
				return moduleWeNeed;
			}
		}
		fileReader.close();
		return null;
	}
	//end
	
	public static void main(String[] args) throws FileNotFoundException {
		//And the greatest example of all time
		File dependencyFile=new File("D:\\HackBulgaria\\Packages\\dependencies.json");
		File packagesFile = new File("D:\\HackBulgaria\\Packages\\all_packages.json");
		File allModules = new File("D:\\HackBulgaria\\Packages\\installed_modules\\");
		Scanner input = new Scanner(System.in);
		String moduleToInstall=input.nextLine();
		resolveDependencies(packagesFile,readDependencies(dependencyFile,moduleToInstall) ,allModules);		
		input.close();
	}

}
