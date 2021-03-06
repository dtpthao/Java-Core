

import java.sql.SQLException;
import java.util.List;

import ua.nure.thao.Practice8.DBManager;
import ua.nure.thao.Practice8.Group;
import ua.nure.thao.Practice8.User;

public class Demo {
     
    private static <T> void printList(List<T> list) {
    		StringBuilder sb = new StringBuilder("[");
        for (T element : list) {
        		sb.append(element.toString() + ", ");
        }
        sb.delete(sb.length() - 2, sb.length()).append("]");
        System.out.println(sb.toString());
    }
     
    public static void main(String[] args) throws SQLException {
         
        DBManager dbManager = DBManager.getInstance();      

        System.out.println("===========================Part 1");
        
        dbManager.insertUser(User.createUser("petrov"));
        dbManager.insertUser(User.createUser("obama"));   
        System.out.print("users ==> ");
        printList(dbManager.findAllUsers());
        System.out.println();
        
        System.out.println("===========================Part 2");
         
        dbManager.insertGroup(Group.createGroup("teamB"));
        dbManager.insertGroup(Group.createGroup("teamC"));   
        System.out.print("groups ==> ");
        printList(dbManager.findAllGroups());
        System.out.println();
        
        System.out.println("===========================Part 3");
        
        User userPetrov = dbManager.getUser("petrov");
        User userIvanov = dbManager.getUser("ivanov");
        User userObama = dbManager.getUser("obama");
         
        Group teamA = dbManager.getGroup("teamA");
        Group teamB = dbManager.getGroup("teamB");
        Group teamC = dbManager.getGroup("teamC");
         
        dbManager.setGroupsForUser(userIvanov, teamA);
        dbManager.setGroupsForUser(userPetrov, teamA, teamB);
        dbManager.setGroupsForUser(userObama, teamA, teamB, teamC);
         
        for (User user : dbManager.findAllUsers()) {
            printList(dbManager.getUserGroups(user));
            System.out.println("~~~~~");
        }
         
        System.out.println("===========================Part 4");
        
        dbManager.deleteGroup(teamA);
        System.out.println();
        
        System.out.println("===========================Part 5");
        
        teamC.setName("teamX");
        dbManager.updateGroup(teamC);

        printList(dbManager.findAllGroups());
    }
}