package com.redwoods.codegen;

import com.redwoods.codegen.config.ApplicationConfig;
import org.springframework.stereotype.Component;

@Component
public class ServiceImplClassGenerator {

    private ApplicationConfig applicationConfig;
    private CodeWriter codeWriter;

    public ServiceImplClassGenerator(ApplicationConfig applicationConfig, CodeWriter codeWriter){
        this.applicationConfig = applicationConfig;
        this.codeWriter = codeWriter;
    }

    public void generateServiceImplClass(String entityName){

        String basePackage = applicationConfig.getPackageName();
        String serviceInterface = entityName+"Service";
        String serviceImplementation = entityName+"ServiceImpl";

        String content = String.format("package %s.service.impl;\n\n" +
                        "import java.util.ArrayList;\n" +
                        "import java.util.List;\n" +
                        "import java.util.Optional;\n\n" +
                        "import org.modelmapper.ModelMapper;\n" +
                        "import org.slf4j.Logger;\n" +
                        "import org.slf4j.LoggerFactory;\n" +
                        "import org.springframework.stereotype.Service;\n\n" +
                        "import %s.dtos.SupplierResponseDto;\n" +
                        "import %s.dtos.SupplierRequestDto;\n" +
                        "import %s.exceptions.NotFoundException;\n" +
                        "import %s.models.Address;\n" +
                        "import %s.models.Supplier;\n" +
                        "import %s.models.SupplierContact;\n" +
                        "import %s.models.SupplierData;\n" +
                        "import %s.repos.SupplierRepository;\n" +
                        "import %s.service.%s;\n\n" +
                        "@Service\n" +
                        "public class %s implements %s {\n\n" +
                        "   private SupplierRepository supplierRepository;\n" +
                        "   private ModelMapper modelMapper;\n" +
                        "    private static final Logger LOGGER = LoggerFactory.getLogger(%s.class);\n\n" +
                        "    public %s(SupplierRepository supplierRepository, ModelMapper modelMapper) {\n" +
                        "        this.supplierRepository = supplierRepository;\n" +
                        "        this.modelMapper = modelMapper;\n" +
                        "    }\n\n" +
                        "    @Override\n" +
                        "    public SupplierResponseDto getSupplier(Long supplierId) throws NotFoundException {\n" +
                        "        try {\n" +
                        "            Optional<Supplier> optionalSupplier = supplierRepository.findById(supplierId);\n" +
                        "            if (optionalSupplier.isEmpty()) {\n" +
                        "                throw new NotFoundException(\"Supplier Doesn't exist.\");\n" +
                        "            }\n\n" +
                        "            return modelMapper.map(optionalSupplier.get(), SupplierResponseDto.class);\n" +
                        "        } catch (Exception ex) {\n" +
                        "            LOGGER.error(\"Error occurred while processing getSupplier\", ex);\n" +
                        "            throw new RuntimeException(\"Error occurred while processing getSupplier\", ex);\n" +
                        "        }\n" +
                        "    }\n\n" +
                        "    @Override\n" +
                        "    public List<SupplierResponseDto> getSuppliers() {\n" +
                        "        try {\n" +
                        "            List<Supplier> suppliers = supplierRepository.findAll();\n" +
                        "            List<SupplierResponseDto> supplierResponseDtos = null;\n" +
                        "            if (!suppliers.isEmpty()) {\n" +
                        "                supplierResponseDtos = new ArrayList<>();\n" +
                        "                for (Supplier supplier : suppliers) {\n" +
                        "                    supplierResponseDtos.add(modelMapper.map(supplier, SupplierResponseDto.class));\n" +
                        "                }\n" +
                        "            }\n" +
                        "            return supplierResponseDtos;\n" +
                        "        } catch (Exception ex) {\n" +
                        "            LOGGER.error(\"Error occurred while processing getSuppliers\", ex);\n" +
                        "            throw new RuntimeException(\"Error occurred while processing getSuppliers\", ex);\n" +
                        "        }\n" +
                        "    }\n\n" +
                        "    @Override\n" +
                        "    public String deleteSupplier(Long supplierId) {\n" +
                        "        if (supplierRepository.findById(supplierId).isPresent()) {\n" +
                        "            supplierRepository.deleteById(supplierId);\n" +
                        "            return \"Supplier Deleted Successfully!!\";\n" +
                        "        }\n" +
                        "        return \"No Supplier exists in the database with the provided id.\";\n" +
                        "    }\n\n" +
                        "    @Override\n" +
                        "    public SupplierResponseDto updateSupplier(Long supplierId, SupplierRequestDto supplierRequestDto) {\n" +
                        "        try {\n" +
                        "            Optional<Supplier> optionalSupplier = supplierRepository.findById(supplierId);\n" +
                        "            if (optionalSupplier.isPresent()) {\n" +
                        "                Supplier supplier = optionalSupplier.get();\n" +
                        "                // Update supplier fields based on supplierRequestDto\n\n" +
                        "                if (supplierRequestDto.getSupplierContact() != null) {\n" +
                        "                    SupplierContact supplierContact = modelMapper.map(supplierRequestDto.getSupplierContact(), SupplierContact.class);\n" +
                        "                    supplier.setSupplierContact(supplierContact);\n" +
                        "                }\n\n" +
                        "                // update address\n" +
                        "                if (supplierRequestDto.getAddress() != null) {\n" +
                        "                    List<Address> addressList = List.of(modelMapper.map(supplierRequestDto.getAddress(), Address[].class));\n" +
                        "                    supplier.setAddress(addressList);\n" +
                        "                }\n\n" +
                        "                // update Supplier data\n" +
                        "                if (supplierRequestDto.getSupplierData() != null) {\n" +
                        "                    List<SupplierData> supplierDataList = List.of(modelMapper.map(supplierRequestDto.getSupplierData(), SupplierData[].class));\n" +
                        "                    supplier.setSupplierData(supplierDataList);\n" +
                        "                }\n\n" +
                        "                // Update other fields\n" +
                        "                // ...\n\n" +
                        "                supplier.setLast_updated_by(\"admin\");\n" +
                        "                supplier.setLast_updated_on(System.currentTimeMillis());\n\n" +
                        "                return modelMapper.map(supplierRepository.save(supplier), SupplierResponseDto.class);\n" +
                        "            } else {\n" +
                        "                return null;\n" +
                        "            }\n" +
                        "        } catch (Exception ex) {\n" +
                        "            LOGGER.error(\"Error occurred while processing updateSuppliers\", ex);\n" +
                        "            throw new RuntimeException(\"Error occurred while processing updateSuppliers\", ex);\n" +
                        "        }\n" +
                        "    }\n\n" +
                        "    @Override\n" +
                        "    public Long addSupplier(Supplier supplier) {\n" +
                        "        try {\n" +
                        "            Supplier newSupplier = supplierRepository.save(supplier);\n" +
                        "            return newSupplier.getId();\n" +
                        "        } catch (Exception ex) {\n" +
                        "            LOGGER.error(\"Error occurred while processing addSuppliers\", ex);\n" +
                        "            throw new RuntimeException(\"Error occurred while processing addSuppliers\", ex);\n" +
                        "        }\n" +
                        "    }\n" +
                        "}\n",
                basePackage, basePackage, basePackage, basePackage, basePackage, basePackage, basePackage, basePackage,
                basePackage, serviceInterface, serviceImplementation, serviceImplementation, serviceImplementation, serviceImplementation, serviceImplementation);

        codeWriter.writeToFile(entityName+"ServiceImpl" + ".java", content);
    }
}
