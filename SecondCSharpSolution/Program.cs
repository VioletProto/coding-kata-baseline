using SecondCSharpSolution.Model;

namespace SecondCSharpSolution;

class Program
{
    static void Main(string[] args)
    {
        
        Cat Feefee = new Cat() { Name = "Feefee" };
        
        Feefee.Meow();
        
        Feefee.KnockSomethingOffTable();

        var result = "i like turtles".ToAngryString();
        Console.WriteLine(result);
        
        Console.WriteLine(100.Double());
    }

    public void LinqStuff()
    {
        // LINQ
        List<Cat> cats = new List<Cat>()
        {
            new Cat() { Id = 2, Name = "Malbac", Color = "Brindle", Weight = 17 },
            new Cat() { Id = 15, Name = "Foofoo", Color = "White", Weight = 25 },
            new Cat() { Id = 50, Name = "Michael", Color = "Green", Weight = 5 },
            new Cat() { Id = 25, Name = "Garfield", Color = "Orange", Weight = 100 },
            new Cat() { Id = 22, Name = "Max", Color = "Orange", Weight = 10 }
        };
        
        var whiteCats = cats.Where(x => x.Color == "White").ToList();
        
        var maxId = cats.Max(x => x.Id);
        
        var averageWeight = cats.Average(x => x.Weight);
        
        var catsByColor = cats.GroupBy(x => x.Color).ToList();

        var fatCats = cats.Where(c => c.Weight > 24).ToList(); // Short syntax/chain syntax

        //Sql form syntax
        var fatCats2 = from c in cats
            where c.Weight > 24
            select c;
    }
}