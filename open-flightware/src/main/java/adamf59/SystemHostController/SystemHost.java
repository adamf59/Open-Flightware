/**
* JAGSAT MISSION 2019
* Written by Adam Frank, with references to contributors at GitHub and Stack Overflow
*  Updates at GitHub.com/adamf59
*
*  Build: 3.21.19a
*/

package adamf59.SystemHostController;

import adamf59.SystemHostController.IO.GPIO;
import adamf59.SystemHostController.Subsystems.Avionics.Avionics;
import adamf59.SystemHostController.Subsystems.Communications.Communications;
import adamf59.SystemHostController.System.Console;
import adamf59.SystemHostController.System.DispatcherService;
import adamf59.SystemHostController.System.SchedulerService;
import adamf59.SystemHostController.System.SystemController;

public class SystemHost {
    
    public static Avionics s_avionics;
    public static Communications s_communications;

    
    private static SchedulerService c_schedulerService;
    private static DispatcherService c_dispatcherService;
    private static SystemController c_systemController;
    

    private static boolean isVerified = false;

    public static void main(String[] args) throws Exception {
        Console.printInfo("OpenFlightware Version 1.0");
        Console.printInfo("------------------------------------------------------------------");

        sys_init();
        


        //getSchedulerService().scheduleTask(new SystemCheckout(), SchedulerService.PRIORITY_HIGH);
        
        

    }

        /**
         * initializes all subsystems and prepares the system for flight
         * @return success (1 or 0)
         */
    public static int sys_init() {
        Console.printInfo("System is now initalizing");
        try {
            c_schedulerService = new SchedulerService();
            c_dispatcherService = new DispatcherService();
            c_systemController = new SystemController();

      
            
            

            GPIO.initGPIOController();


        } catch(Exception e) {
                Console.printErr("Exception thrown in system initialization: " + " >> " + e.getCause());
                e.printStackTrace();
            // something went wrong during initialization
            return 0; 
        }
        // initializing system was successful
        Console.printOk("System initialization complete");
        return 1;
    }

    /**
     * Destroys all subsystems
     */
    public static void sys_destroy() {
        Console.printWarn("System is shutting down");

        s_avionics.destroySubsystem();
        s_communications.destroySubsystem();
        GPIO.shutdown();
    }
 
      

        /**
         * Get the avionics subsystem instance
         */
    public static Avionics getAvionics() {
        return s_avionics;
    }

        /**
         * Get the communications subsystem instance
         */
    public static Communications getCommunications() {
        return s_communications;
    }

        /**
         * Get the active SchedulerService instance
         */
    public static SchedulerService getSchedulerService() {
        return c_schedulerService;
    }
     /**
         * Get the active DispatcherService instance
         */
    public static DispatcherService getDispatcherService() {
        return c_dispatcherService;
    }

    /**
     * returns 
     * @return whether JFSL is running on command of the JFS Launcher (real instance vs. IDE instance)
     */
    public static boolean isJFSLVerified() {
        return isVerified;
    }





   

}