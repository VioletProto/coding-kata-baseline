namespace ConsoleInterfacePractice;

public class PrettyConsole : IFunConsole
{
    public string PromptForInput()
    {
        Console.BackgroundColor = ConsoleColor.Blue;
        while (true)
        {
            Console.WriteLine("Enter something, anything please");
            try
            {
                // Take users input
                string? input = Console.ReadLine();
                    
                if (input != null)
                {
                    // Make sure it is valid
                    if (!string.IsNullOrEmpty(input))
                    {
                        // return result;
                        return input;
                    }
                        
                    Console.WriteLine("input cannot be empty");
                }
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
            }
        }
    }

    public void PrintInput(string input)
    {
        Console.BackgroundColor = ConsoleColor.Magenta;
        Console.WriteLine(input);
        Console.ResetColor();
    }
}