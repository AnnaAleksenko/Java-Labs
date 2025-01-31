import java.util.Scanner;

public class Main
{
    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args)
    {
        while (true)
        {
            System.out.print("Введите номер задачи ;)");
            int num = in.nextInt();
            switch (num)
            {
                case 1:
                    zadanie1();
                case 2:
                    zadanie2();
                case 3:
                    zadanie3();
                case 4:
                    zadanie4();
                case 5:
                    zadanie5();
            }
        }
    }

    static void zadanie1()
    {
        System.out.print("Введите натуральное число:");
        int n = in.nextInt();
        int step = 0;
        while (n > 1)
        {
            if (n % 2 == 0)
            {
                n = n / 2;
            }
            else
            {
                n = (3 * n) + 1;
            }
            step++;
        }
        System.out.println("Количество шагов: " + step);
    }

    static void zadanie2()
    {
        System.out.println("Введите натуральное число:");
        int n = in.nextInt();
        int result = 0;

        for (int i = 0; i < n; i++)
        {
            int actualnum = in.nextInt();
            if (i % 2 == 0)
            {
                result += actualnum;
            }
            else
            {
                result -= actualnum;
            }
        }
        System.out.println("Сумма ряда: " + result);
    }

    static void zadanie3()
    {

        System.out.println("Введите координаты клада: ");
        int endX = in.nextInt();
        int endY = in.nextInt();
        in.nextLine();

        int x_0 = 0;
        int y_0 = 0;

        int count = 0;
        int minCommand = Integer.MAX_VALUE;

        System.out.println("Введите команды и кол-во шагов: ");
        while (true)
        {
            String command = in.nextLine();
            if (command.equals("стоп"))
            {
                break;
            }

            int steps = in.nextInt();
            in.nextLine();

            switch (command)
            {
                case "север":
                    y_0 += steps;
                    break;
                case "юг":
                    y_0 -= steps;
                    break;
                case "восток":
                    x_0 += steps;
                    break;
                case "запад":
                    x_0 -= steps;
                    break;
                default:
                    System.out.println("Неверное направление: " + command);
                    continue;
            }

            count++;

            if (x_0 == endX && y_0 == endY)
            {
                minCommand = Math.min(minCommand, count);
            }
        }

        if (minCommand == Integer.MAX_VALUE)
        {
            System.out.println("Клад не найден, карта не верна. Текущие координаты: (" + x_0 + ", " + y_0 + ")");
        }
        else
        {
            System.out.println("Минимальное кол-во шагов: " + minCommand);
        }

    }

    static void zadanie4()
    {
        System.out.println("Введите кол-во дорог:");
        int numroad = in.nextInt();
        int maxheight = 0;
        int finalroad  = 0;

        for (int i = 1; i <= numroad; i++)
        {
            System.out.println("Введите кол-во туннелей для " + i + " дороги:");
            int numSub = in.nextInt();
            System.out.println("Введите высоты:");
            int minHeight = Integer.MAX_VALUE;

            for (int j = 0; j < numSub; j++)
            {
                int subHeight = in.nextInt();
                if (subHeight < minHeight)
                {
                    minHeight = subHeight;
                }
            }

            if (minHeight > maxheight)
            {
                maxheight = minHeight;
                finalroad = i;
            }
        }
        System.out.println(finalroad + " " + maxheight);
    }

    static void zadanie5()
    {
        System.out.println("Введите целое трехзначное число:");
        int number = in.nextInt();
        int digit1 = number / 100;
        int digit2 = (number / 10) % 10;
        int digit3 = number % 10;

        if (number > 99 && number < 1000)
        {
            int summ = digit1 + digit2 + digit3;
            int multipli = digit1 * digit2 * digit3;

            if ((summ % 2 == 0) && (multipli % 2 == 0))
            {
                System.out.println("Число является дважды четным!");
                System.out.println("Сумма цифр: " + summ);
                System.out.println("Произведение цифр: " + multipli);
            }
            else
            {
                System.out.println("Число не является дважды четным.");
            }
        }
        else
        {
            System.out.println("Некорректно введенное число");
        }
    }
}