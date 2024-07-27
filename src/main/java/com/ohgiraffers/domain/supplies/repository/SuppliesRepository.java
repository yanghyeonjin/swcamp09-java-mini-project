package com.ohgiraffers.domain.supplies.repository;

import com.ohgiraffers.domain.product.aggregate.Product;
import com.ohgiraffers.domain.supplies.aggregate.Supplies;
import com.ohgiraffers.domain.supplies.stream.MyObjectOutput;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class SuppliesRepository {

    private ArrayList<Supplies> allSupplies = new ArrayList<>();
    private static final String FILE_PATH = "src/main/java/com/ohgiraffers/domain/supplies/db/suppliesList.dat";

    public SuppliesRepository() {
        // 현재 비품 목록을 불러온다.
        loadFile();
    }

    private void loadFile() {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            ObjectInputStream objectInputStream = null;
            try {
                objectInputStream = new ObjectInputStream(
                        new BufferedInputStream(
                                new FileInputStream(file)
                        )
                );

                // 파일의 끝까지 읽는다.
                while (true) {
                    allSupplies.add((Supplies) objectInputStream.readObject());
                }
            } catch (EOFException e) {
                System.out.println("[비품 목록] 파일 로딩 완료");
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    if (objectInputStream != null) objectInputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    // 전체 비품 목록 조회하기
    public ArrayList<Supplies> selectAllSupplies() {
        return allSupplies;
    }

    // 번호로 비품 조회하기
    public Supplies selectSuppliesByNo(int no) {
        for (Supplies supplies : allSupplies) {
            if (supplies.getSuppliesNo() == no) {
                return supplies;
            }
        }

        return null;
    }

    // 비품 1개 새로 생성하기
    public int insertSupplies(Product selectedProduct, int quantity) {
        Supplies newSupplies = new Supplies();
        newSupplies.setSuppliesNo(selectedProduct.getProductNo());
        newSupplies.setSuppliesName(selectedProduct.getProductName());
        newSupplies.setQuantity(quantity);
        newSupplies.setPurchaseDate(LocalDate.now());

        // 컬렉션에 추가
        allSupplies.add(newSupplies);

        // 파일에 추가
        ArrayList<Supplies> addSuppliesList = new ArrayList<>();
        addSuppliesList.add(newSupplies);
        saveToFile(addSuppliesList, false);

        return 1;
    }

    private void saveToFile(ArrayList<Supplies> suppliesList, boolean forceWrite) {
        File suppliesFile = new File(FILE_PATH);

        if (forceWrite || !suppliesFile.exists()) {
            writeFile(suppliesList);
        } else {
           appendDataToFile(suppliesList);
        }
    }

    // 파일을 덮어쓰기 한다.
    private void writeFile(ArrayList<Supplies> suppliesList) {
        File suppliesFile = new File(FILE_PATH);

        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream(suppliesFile)
                    )
            );

            // 객체 하나씩 순회해서 파일에 출력
            for (Supplies supplies : suppliesList) {
                objectOutputStream.writeObject(supplies);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (objectOutputStream != null) objectOutputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // 데이터를 파일에 추가한다.
    private void appendDataToFile(ArrayList<Supplies> suppliesList) {
        File suppliesFile = new File(FILE_PATH);

        MyObjectOutput myObjectOutput = null;
        try {
            myObjectOutput = new MyObjectOutput(
                    new BufferedOutputStream(
                            new FileOutputStream(suppliesFile, true)
                    )
            );

            for (Supplies supplies : suppliesList) {
                myObjectOutput.writeObject(supplies);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (myObjectOutput != null) myObjectOutput.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // 특정 비품의 재고 수량을 증가시킨다.
    public int increaseSuppliesQuantity(int suppliesNo, int quantity) {
        // 컬렉션 수정
        for (Supplies supplies : allSupplies) {
            if (supplies.getSuppliesNo() == suppliesNo) {
                supplies.setQuantity(supplies.getQuantity() + quantity);

                // 파일 수정
                saveToFile(allSupplies, true);

                return 1;
            }
        }

        return 0;
    }
}
