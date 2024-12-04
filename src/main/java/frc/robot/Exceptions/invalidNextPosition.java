package frc.robot.Exceptions;

public class invalidNextPosition extends Exception
{
    public invalidNextPosition(String message)
    {
        super(message);
    }

    public void invalidNextPositionMessage()
    {
        System.out.println("Stayyy, ur_doing_something_wrong.exe");
        System.out.println("No next position detected, meaning you are outside of limit switch range");
        System.out.println("This is very bad and you sould probably get it looked at in Constants.java");
    }
}
