using Bogus;
using Spectre.Console;

namespace NuGetPractace
{
    class Program
    {
        static void Main(string[] args)
        {
            var npcFaker = new Faker<NPC>()
                .RuleFor(n => n.Name, f => f.Name.FullName())
                .RuleFor(n => n.Class, f => f.PickRandom(new[] { "Warrior", "Mage", "Rogue", "Cleric", "Ranger" }))
                .RuleFor(n => n.Level, f => f.Random.Int(1, 20))
                .RuleFor(n => n.HP, (f, n) => f.Random.Int(50, 100) + (n.Level * 5))
                .RuleFor(n => n.Gold, f => f.Random.Int(10, 500));

            var npcs = npcFaker.Generate(5);

            var table = new Table();
            table.Border(TableBorder.Rounded);
            table.Title("[Yellow]Generated NPCs[/]");
            
            table.AddColumn("[cyan]Name[/]");
            table.AddColumn("[green]Class[/]");
            table.AddColumn("[red]Level[/]");
            table.AddColumn("[cyan]HP[/]");
            table.AddColumn("[gold1]Gold[/]");

            foreach (var npc in npcs)
            {
                table.AddRow(
                    npc.Name,
                    npc.Class, 
                    npc.Level.ToString(), 
                    npc.HP.ToString(), 
                    npc.Gold.ToString()
                    );
            }
            
            AnsiConsole.Write(
                new Panel("[bold underline magenta]Welcome to the NPC Generator[/]")
                    .Border(BoxBorder.Double)
                    .Padding(1, 1)
                );
            
            AnsiConsole.WriteLine();
            
            AnsiConsole.Write(table);
            
            AnsiConsole.WriteLine();
            
            AnsiConsole.MarkupLine("[italic grey]Press any key to exit[/]");
            Console.ReadKey();
        }
    }

    class NPC
    {
        public string Name { get; set; }
        public string Class { get; set; }
        public int Level { get; set; }
        public int HP { get; set; }
        public int Gold { get; set; }
    }
}

