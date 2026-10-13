namespace ConsoleInterfacePractice;

public class PirateConsole : IFunConsole
{
    public string PromptForInput()
    {
        while (true)
        {
            Console.WriteLine("Ahoy, enter ye here some worthwhile news and I’ll share with ye me rum! Savvy?");
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
        var pirateString = input.ToLower().Replace("r", "-ARR-");
        Console.WriteLine(pirateString + ", me hearty!");
    }
}