namespace ConsoleInterfacePractice;

public class AngryConsole : IFunConsole
{
    public string PromptForInput()
    {
        while (true)
        {
            Console.WriteLine("Say something, I dear you, I triple dog dare you! Do you understand the words that are coming out of my mouth!");
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
        Console.WriteLine(input.ToUpper());
    }
}