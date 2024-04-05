import java.util.ArrayList;

public class StockAnalysis {

    // method to calculateAveragePrice
    public static float calculateAveragePrice(float[] stockPrices) {
        float sum = 0;

        for (float price : stockPrices) {
            sum += price;
        }

        return sum / stockPrices.length;
    }

    // calculateAveragePrice method for ArrayList
    public static float calculateAveragePriceForAl(ArrayList<Float> stockPrices) {
        float sum = 0;

        for (float price : stockPrices) {
            sum += price;
        }

        return sum / stockPrices.size();
    }

    // method to findMaximumPrice
    public static float findMaximumPrice(float[] stockPrices) {
        float maxPrice = stockPrices[0];

        for (float price : stockPrices) {
            if (price > maxPrice) {
                maxPrice = price;
            }
        }

        return maxPrice;
    }

    // findMaximumPrice method for ArrayList
    public static float findMaximumPriceForAl(ArrayList<Float> stockPrices) {
        float maxPrice = stockPrices.get(0);

        for (float price : stockPrices) {
            if (price > maxPrice) {
                maxPrice = price;
            }
        }

        return maxPrice;
    }

    // method to countOccurrences
    public static int countOccurrences(float[] stockPrices, float targetPrice) {
        int count = 0;

        for (float price : stockPrices) {
            if (price == targetPrice) {
                count++;
            }
        }

        return count;
    }

    // method to computeCumulativeSum
    public static ArrayList<Float> computeCumulativeSum(ArrayList<Float> stockPrices) {
        ArrayList<Float> cumulativeSum = new ArrayList<>();
        float sum = 0;

        for (float price : stockPrices) {
            sum += price;
            cumulativeSum.add(sum);
        }

        return cumulativeSum;
    }

    public static void main(String[] args) {
        // Example usage
        float[] stockPricesArray = { 25.5f, 28.5f, 24.8f, 27.3f, 26.7f, 28.5f, 25.5f, 30.2f, 28.5f, 27.3f };
        ArrayList<Float> stockPricesArrayList = new ArrayList<>();

        // Initialize ArrayList with the same data as in the array
        for (float price : stockPricesArray) {
            stockPricesArrayList.add(price);
        }

        // Calculate average price
        float averagePrice = calculateAveragePrice(stockPricesArray);
        System.out.println("Average Stock Price: " + averagePrice);

        // Calculate average price for ArrayList
        float averagePriceArrayList = calculateAveragePriceForAl(stockPricesArrayList);
        System.out.println("Average Stock Price (ArrayList): " + averagePriceArrayList);

        // Find maximum price
        float maxPrice = findMaximumPrice(stockPricesArray);
        System.out.println("Maximum Stock Price: " + maxPrice);

        // Find maximum price for ArrayList
        float maxPriceArrayList = findMaximumPriceForAl(stockPricesArrayList);
        System.out.println("Maximum Stock Price (ArrayList): " + maxPriceArrayList);

        // Count occurrences of a specific price
        float targetPrice = 28.5f;
        int occurrences = countOccurrences(stockPricesArray, targetPrice);
        System.out.println("Occurrences of " + targetPrice + ": " + occurrences);

        // Compute cumulative sum of stock prices using ArrayList
        ArrayList<Float> cumulativeSum = computeCumulativeSum(stockPricesArrayList);
        System.out.println("Cumulative Sum of Stock Prices: " + cumulativeSum);
    }
}