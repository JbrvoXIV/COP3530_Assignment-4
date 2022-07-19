public class  FindMedianDriver {

    public static double findMedian(int[] list1, int[] list2) {
        int high = list1.length, low = 0, mid = low + ((high - low) / 2), m1; // pointers for list1
        int highTwo = list2.length, lowTwo = 0, midTwo = lowTwo + ((highTwo - lowTwo) / 2), m2; // pointers for list2

        while(high - low > 2 && highTwo - lowTwo > 2) { // loop while size of both arrays > 2
            if((high - low) % 2 == 0) // if size of list1 even or odd, assign median of list1
                m1 = (list1[mid] + list1[mid - 1]) / 2;
            else
                m1 = list1[mid];
            if((highTwo - lowTwo) % 2 == 0) // if size of list2 even or odd, assign median of list2
                m2 = (list2[midTwo] + list2[midTwo - 1]) / 2;
            else
                m2 = list2[midTwo];

            if(m1 == m2) // if both median are the same, return
                return m1;

            if(m1 > m2) { // if median of list1 > median of list2, search list[0 -> mid] and list2[midTwo -> highTwo]
                high = mid;
                mid = low + ((high - low) / 2);
                lowTwo = midTwo;
                midTwo = lowTwo + ((highTwo - lowTwo) / 2);
            }
            if(m2 > m1) { // if median of list2 > median of list1, search list1[mid -> high] and list2[0 -> midTwo]
                low = mid;
                mid = low + ((high - low) / 2);
                highTwo = midTwo;
                midTwo = lowTwo + ((highTwo - lowTwo) / 2);
            }
        }
        return (Math.max(list1[low], list2[lowTwo]) + Math.min(list1[mid], list2[midTwo])) / 2.0; // return median
    }

    public static void main(String[] args) {
        int[] list1 = { 3, 5, 8, 9 };
        int[] list2 = { 2, 6, 10, 12 };
        System.out.printf("Median from list1 and list2 after union: %,.2f\n", findMedian(list1, list2));
    }
}