namespace ConsoleInterfacePractice;

class Program
{
    static void Main(string[] args)
    {
        List<IFunConsole> consoles = new List<IFunConsole>();
        PirateConsole pConsole = new PirateConsole();
        PrettyConsole pConsole2 = new PrettyConsole();
        AngryConsole aConsole = new AngryConsole();
        
        consoles.Add(pConsole);
        consoles.Add(pConsole2);
        consoles.Add(aConsole);

        foreach (IFunConsole console in consoles)
        {
            string input = console.PromptForInput();
            console.PrintInput(input);
        }
    }
}