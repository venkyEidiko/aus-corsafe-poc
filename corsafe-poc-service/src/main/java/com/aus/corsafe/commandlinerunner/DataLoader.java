package com.aus.corsafe.commandlinerunner;

import com.aus.corsafe.entity.Products;
import com.aus.corsafe.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Integer> productIds = Arrays.asList(1, 2, 3, 4, 5,6,7);
        List<String> names = Arrays.asList("Canon R100 Mirrorless",
                "DELL SE-Series 68.58 cm", "Apple iPhone 15",
                "LG 32LMBPTC 80 cm", "Apple MacBook AIR Apple M2",
                "HexaGear Collar Microphone","Apple MC747HN/A charger");
        List<String> descriptions = Arrays.asList(
                "This Canon R100 Mirrorless Camera features a RF-S 18-45 mm lens",
                "Stylish and Portable Design",
                "Experience the iPhone 15 – your dynamic companion.",
                "You can conveniently browse, stream, and watch content on the LG Smart LED TV. ",
                "Charged in a blazing-fast speed with the next-level M2 chip",
                "Collar Microphone Works well with DSLR Camera",
                "These sellers are Apple-authorized resellers"
        );
        List<Double> prices = Arrays.asList(49500.00, 12568.99, 59500.00,29500.00,
                84500.00,890.36,1500.99);
        List<Integer> stockQuantities = Arrays.asList(1, 1, 1, 1, 1,1,1);
        List<String> imageNames = Arrays.asList(
                "camera.jpeg",
                "dellLaptop.jfif",
                "iphone15pro.jfif",
                "LgTv.jfif",
                "mackBook.webp",
                "microphones.jpeg",
                "mackBookCharger.jfif"
        );

        for (int i = 0; i < names.size(); i++) {
            try {

                Products existingProduct = productRepository.findByProductId(productIds.get(i));


                if (existingProduct == null) {
                    Products product = new Products();
                    product.setProductId(productIds.get(i));
                    product.setName(names.get(i));
                    product.setDescription(descriptions.get(i));
                    product.setPrice(prices.get(i));
                    product.setStockQuantity(stockQuantities.get(i));
                    product.setProductImage(loadImage(imageNames.get(i))); // Load the image

                    productRepository.save(product);
                    System.out.println("Inserted: " + product.getName());
                } else {
                    System.out.println("Product ID " + productIds.get(i) + " already exists. Skipping...");
                }

            } catch (Exception e) {
                // Print the stack trace if an exception occurs
                e.printStackTrace();
            }
        }

        System.out.println("Data loading complete!");
    }

    private byte[] loadImage(String imageName) throws IOException {
        Path imagePath = Paths.get("src/main/resources/image/" + imageName);
        return Files.readAllBytes(imagePath);
    }
}
