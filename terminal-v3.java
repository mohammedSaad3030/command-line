import java.io.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.nio.file.*;
import java.util.Scanner;


///https://www.oreilly.com/library/view/java-for-dummies/9781118239742/a81_07_9781118239742-ch04.html
///https://www.journaldev.com/830/java-delete-file-directory
///http://www.java2s.com/Code/Java/JDK-7/UsingtheSimpleFileVisitorclasstotraversefilesystems.htm
class Traverse extends SimpleFileVisitor<Path>
{
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) throws IOException {
        Files.delete(file);
        return FileVisitResult.CONTINUE;
    }
    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException e) throws IOException {
        Files.delete(dir);
        return FileVisitResult.CONTINUE;
    }
}

public class Commands {
    static String Default = "/home/saad/";

    public static void mkdir(ArrayList<String> arr) throws DirectoryExists {
        String argument = arr.get(0);
        File dir = new File(Default + argument);
        boolean status = dir.mkdirs();
        if (status) System.out.println("Directory created successfully");
        else {
            throw new DirectoryExists("Failed to create the directory!\n " +
                    "Make sure the directory are not already exist");
        }
    }

    public static void rmdir(ArrayList<String> arr) throws DirectoryNotEmpty {
        if (arr.size() != 1) {
            ///throw new Exception();
        }
        String argument = arr.get(0);
        File dir = new File(Default + argument);
        boolean status = dir.delete();
        if (status) System.out.println("Directory deleted successfully");
        else {
            throw new DirectoryNotEmpty("Failed to delete the directory!\n " +
                    "Make sure the directory are empty");
        }
    }

    public static void mv(ArrayList<String> arr) throws IOException, DirectoryWithTheSameName, NoSuchFileOrDirectory {
        String source = arr.get(0);
        String Destination = arr.get(1);
        File sourceDir = new File(Default + source);
        File destenationDir = new File(Default + Destination);
        if (!sourceDir.exists()) {
            throw new NoSuchFileOrDirectory("No such file with the same name to move");
        }
        if (destenationDir.exists()) {
            throw new DirectoryWithTheSameName("There is a file with the same name");
        } else {
            Path temp = Files.move(Paths.get(Default + source), Paths.get(Default + Destination));
            System.out.println("File moved successfully");
        }
    }

    public static void rm(ArrayList<String> arr) throws IOException, NoSuchFileOrDirectory {
        String argument = arr.get(0);
        //SimpleFileVisitor is the default implementation of the FileVisitor interface
        Path dir = Paths.get(Default + argument);
        Traverse traverse = new Traverse();
        Files.walkFileTree(dir, traverse);
    }

    public static void args(ArrayList<String> arr) throws NoSuchcommand {
        String argument = arr.get(0);
        switch(argument)
        {
            case "cp":
            case "mv":
            {
                System.out.println("arg1 SourcePath, arg2 DestinationPath");
                break;
            }
            case "cd":
            {
                System.out.println("arg1 DesirablePath or no arguments : DefaultPath");
                break;
            }
            case "ls":
            {
                System.out.println("arg1 option or no argument");
                break;
            }
            case "clear": case "pwd": case "|": case ">": case "<": case ">>":
            {
                System.out.println("no arguments");
                break;
            }
            case "rm":
            {
                System.out.println("arg1 RemovedFileOrDirectory");
                break;
            }
            case "mkdir": case "rmdir" :
            {
                System.out.println("arg1 DirectoryPath");
                break;
            }
            case "date":
            {
                System.out.println("arg1 settedDateor no argumen : show data");
                break;
            }
            case "echo":
            {
                System.out.println("arg1 printedArgument");
                break;
            }
            case "cat":
            {
                System.out.println("arg1 firstFile, arg2 secondFile or arg1 only : show content of the file");
                break;
            }
            case "more": case "less":
            {
                System.out.println("arg1 showedFile");
                break;
            }
            default:
            {
                throw new NoSuchcommand("No such commnad!");
            }
        }

    }

    public static void main(String args[]) throws IOException, NoSuchFileOrDirectory, DirectoryExists
    {


            ArrayList<String> arr = new ArrayList<String>();
            arr.add("rmdir");
        try {
            args(arr);
        } catch (NoSuchcommand e) {
            e.getMessage();
        }
            /*
            arr.add("Desktop/hhhh");
            arr.add("Documents/hhhh");

            try
            {
                mv(arr);
            }catch (NoSuchFileOrDirectory e)
            {
                System.out.println(e.getMessage());
            } catch(DirectoryWithTheSameName e)
            {
                System.out.println(e.getMessage());
                System.out.println("Do you want to replace it ? [Y or N]");
                Scanner cin = new Scanner(System.in);
                String res = cin.nextLine();
                if(res.equals("Y") || res.equals("y"))
                {
                    ArrayList<String> arr2 = new ArrayList<>();
                    arr2.add(arr.get(1));

                    rm(arr2);
                    try {
                        mv(arr);
                    } catch (DirectoryWithTheSameName exp) {
                        System.out.println(exp.getMessage());
                        System.out.println("why ha");
                    }
                }
            }*/
    }
}
