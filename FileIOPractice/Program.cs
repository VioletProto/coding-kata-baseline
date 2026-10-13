using System.Text.Json;
using FileIOPractice.Models;

namespace FileIOPractice
{

    class Program
    {
        public static List<Superhero> superheroes = new List<Superhero>();
        static void Main(string[] args)
        {
            superheroes.Add(new Superhero { Name = "Bruce Wayne", Alias = "Batman", PowerLevel = 85, IsActive = true });
            superheroes.Add(new Superhero { Name = "Clark Kent", Alias = "Superman", PowerLevel = 100, IsActive = true });
            superheroes.Add(new Superhero { Name = "Diana Prince", Alias = "Wonder Woman", PowerLevel = 98, IsActive = true });
            superheroes.Add(new Superhero { Name = "Peter Parker", Alias = "Spider-Man", PowerLevel = 88, IsActive = true });
            superheroes.Add(new Superhero { Name = "Tony Stark", Alias = "Iron Man", PowerLevel = 90, IsActive = false });
            
            SaveSuperheroes("superheroes.json", superheroes);
            
            SetHerosEmpty();
            
            superheroes = LoadSuperheroes("superheroes.json");
            
            foreach (var hero in superheroes)
            {
                Console.WriteLine($"{hero.Name} - {hero.Alias}");
            }
        }

        static List<Superhero> LoadSuperheroes(string path)
        {
            if (!File.Exists(path))
            {
                File.WriteAllText(path, string.Empty);
                return new List<Superhero>();
            }

            var json = File.ReadAllText(path);

            if (string.IsNullOrWhiteSpace(json))
            {
                return new List<Superhero>();
            }
            
            var results = JsonSerializer.Deserialize<List<Superhero>>(json);
            
            return results ?? new List<Superhero>();
        }
        
        static void SaveSuperheroes(string path, List<Superhero> superheroes)
        {
            string json = JsonSerializer.Serialize(superheroes,
                new JsonSerializerOptions() { WriteIndented = true });
            File.WriteAllText(path, json);
            Console.WriteLine(json);
        }

        static void SetHerosEmpty()
        {
            superheroes = new List<Superhero>();
        }
    }
}
