package frc.robot.Exceptions;

public class invalidCrateMotorOutput extends Exception
{
    public invalidCrateMotorOutput(String message)
    {
        super(message);
    }

    public void emergencyStopMessage()
    {
      System.out.println("WARNING: software fail safe triggered to stop CratePickerUper");
      System.out.println("Do not continue to run CratePickerUper while this error occurs");
      System.out.println("It is possible to destory robot!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }
    
}
