using SecondCSharpSolution.Model;

namespace SecondCSharpSolution
{

    public static class CatExtensions
    {
        public static void KnockSomethingOffTable(this Cat cat)
        {
            Console.WriteLine($"{cat.Name} knocks something off table") ;
        }
    }
}