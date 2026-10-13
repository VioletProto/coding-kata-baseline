namespace SecondCSharpSolution
{

    public static class OtherExtensions
    {
        public static string ToAngryString(this string theString)
        {
            return theString.ToUpper() + "!!!!!!!!!!";
        }
        
        public static int Double(this int theInt)
        {
            return theInt * 2;
        }
    }
}