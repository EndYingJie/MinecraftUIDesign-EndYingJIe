package net.endyingjie.ui_desgin.windows.project_main;

import com.squareup.javapoet.*;
import net.endyingjie.ui_desgin.Main;
import net.endyingjie.ui_desgin.configs.Config;
import net.endyingjie.ui_desgin.manager.SlotGroupManage;
import net.endyingjie.ui_desgin.manager.SlotManager;
import net.endyingjie.ui_desgin.manager.interface_manager.SimpleSlotInformation;
import net.endyingjie.ui_desgin.manager.interface_manager.SlotInformation;
import net.endyingjie.ui_desgin.resources.LangReading;
import net.endyingjie.ui_desgin.resources.ResourceImageIcon;
import net.endyingjie.ui_desgin.windows.lables.SimpleSlotGroup;
import net.endyingjie.ui_desgin.windows.lables.SlotGroupInformation;
import net.endyingjie.ui_desgin.windows.lables.SlotGroupLabel;
import net.endyingjie.ui_desgin.windows.other.LoadingWindow;
import net.endyingjie.ui_desgin.windows.presonalized.PersonalizedButton;
import net.endyingjie.ui_desgin.windows.jdialog_windows.config_windows.VersionForge;
import net.endyingjie.ui_desgin.windows.lables.SlotLabel;
import net.endyingjie.ui_desgin.windows.presonalized.PersonalizedTextField;
import net.endyingjie.ui_desgin.windows.project_main_interface_save_project.MenuProject;
import net.endyingjie.ui_desgin.windows.project_main_interface_save_project.SimpleMenuProject;

import javax.lang.model.element.Modifier;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public final class MenuProjectWindow extends JFrame {
    private static final LangReading lang = LangReading.create(LangReading.Model.LANG);
    private final JPanel configPanel = new JPanel(){
        @Override
        public void paint(Graphics g){
            super.paint(g);
            g.setColor(new Color(0x3D3D3D));
            for(int i=0;i<16;++i) g.drawLine(0, this.getHeight()-181-i, this.getWidth(), this.getHeight()-181-i);
        }
    };
    private final ArrayList<Integer> removeSlotIndex = new ArrayList<>();
    private final ArrayList<Integer> removeOutputIndex = new ArrayList<>();
    private final ArrayList<Integer> removeInventoryIndex = new ArrayList<>();
    private final PersonalizedButton copySlotButton = new PersonalizedButton();
    private int outputIndexAll = 0;
    private final PersonalizedButton DetailTweaksOpen = new PersonalizedButton();
    private final JComboBox<SlotType> slotTypeChoose = new JComboBox<>();
    private double OnePixel=0.0;
    private final JLabel slotTip = new JLabel("",JLabel.CENTER);
    private final PersonalizedButton removeSlotButton = new PersonalizedButton();
    private final PersonalizedButton addNewGuiButton  =new PersonalizedButton();
    private final ArrayList<Runnable> slotsSet = new ArrayList<>();
    public final FileDialog saveProjectFileDialog = new FileDialog(new Frame(),lang.getContent("save_project_file_dialog"),1);
    private ImageIcon main_image;
    private final PersonalizedButton ImportPngButton = new PersonalizedButton();
    private final JLabel mainLabel = new JLabel();
    public final JLayeredPane MainPaintingArea = new JLayeredPane();
    private final PersonalizedButton saveProjectButton = new PersonalizedButton();
    private String mainImagePath;
    private int NowIndex = 0;
    private final Logger logger = Logger.getLogger("Menu Project");
    private final ArrayList<SlotLabel> slots = new ArrayList<>();
    private int main_image_weight=0;
    private int main_image_height=0;
    private int indexSlotAll = 0;
    private final PersonalizedTextField inputSlotX = new PersonalizedTextField();
    private final PersonalizedTextField inputSlotY = new PersonalizedTextField();
    private final PersonalizedTextField inputIndexSlot = new PersonalizedTextField();
    private final PersonalizedButton rightSlotButton  =new PersonalizedButton();
    private final PersonalizedButton rightLocationButton = new PersonalizedButton();
    private final LoadingWindow loadingWindow = new LoadingWindow();
    private final PersonalizedButton exportJavaFile = new PersonalizedButton();
    private int indexSlotAllInventory=0;
    private final PersonalizedButton SlotGroupConfigurationButton = new PersonalizedButton();
    private int indexSlotGroupAll = 0;
    private int nowIndexSlotGroup  =0;
    private final ArrayList<SlotGroupLabel> slotGroups =new ArrayList<>();
    private final ArrayList<Runnable> slotGroupSet =new ArrayList<>();
    private final PersonalizedButton SynchronousSlotButton = new PersonalizedButton(lang.getContent("synchronous_slot_button"));
    public MenuProjectWindow(String project_name, VersionForge version,boolean isoVisible) {
        super(lang.getContent("menu_project_title").concat("-").concat(project_name).concat("-").concat(version.toString()).concat("-").concat(lang.getContent("MainWindowTitle")));
        this.MainPaintingArea.setLayout(null);
        this.setLayout(null);
        configPanel.setLayout(null);
        this.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                configPanel.setBounds(getWidth()-300,0,300,getHeight());
                ImportPngButton.setLocation(0,configPanel.getHeight()-180);
                exportJavaFile.setLocation(0,configPanel.getHeight()-120);
                saveProjectButton.setLocation(0,configPanel.getHeight()-150);
                MainPaintingArea.setBounds((getWidth()-300-getHeight())/2,0,getHeight(),getHeight());
                mainLabel.setBounds(new Rectangle(new Point(0,0),MainPaintingArea.getSize()));
                if(main_image!=null){
                    ImageIcon main_image1 = new ImageIcon(main_image.getImage().getScaledInstance(MainPaintingArea.getHeight(), MainPaintingArea.getHeight(), Image.SCALE_FAST));
                    mainLabel.setIcon(main_image1);
                    OnePixel =((double)MainPaintingArea.getHeight())/256.0;
                    main_image_weight = main_image1.getIconWidth();
                    main_image_height = main_image1.getIconHeight();
                }
                for(Runnable run:slotsSet){
                    run.run();
                }
                for(Runnable run:slotGroupSet){
                    run.run();
                }
            }
            @Override
            public void componentMoved(ComponentEvent e) {

            }
            @Override
            public void componentShown(ComponentEvent e) {

            }
            @Override
            public void componentHidden(ComponentEvent e) {

            }
        });
        this.getContentPane().setBackground(new Color(0x3D3D3D));
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                Main.mainWindows.setVisible(true);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

        slotTypeChoose.setForeground(Color.WHITE);
        slotTypeChoose.setBackground(new Color(0x474747));
        slotTypeChoose.setBounds(0,180,300,30);
        for(SlotType slotType:SlotType.values()){
            slotTypeChoose.addItem(slotType);
        }
        this.configPanel.add(slotTypeChoose);
        configPanel.setBounds(this.getWidth()-300,0,300,this.getHeight());
        OnePixel =((double)MainPaintingArea.getHeight())/256.0;
        this.configPanel.setBackground(new Color(0x888888));
        this.add(MainPaintingArea);
        ImportPngButton.setText(lang.getContent("import_png"));
        Frame fileDialogFrame = new Frame();
        FileDialog fileDialog = new FileDialog(fileDialogFrame, lang.getContent("import_png"), FileDialog.LOAD);
        ImportPngButton.addActionListener(event->{
            fileDialog.setVisible(true);
            Thread thread = new Thread(()->{
                while(true){
                    if(!fileDialog.isVisible()){
                        try {
                            main_image = new ImageIcon(fileDialog.getDirectory().concat(fileDialog.getFile()));
                            ImageIcon main_image1 = new ImageIcon(main_image.getImage().getScaledInstance(this.MainPaintingArea.getHeight(), this.MainPaintingArea.getHeight(), Image.SCALE_SMOOTH));
                            mainImagePath = fileDialog.getDirectory().concat(fileDialog.getFile());
                            mainLabel.setIcon(main_image1);
                            mainImagePath = fileDialog.getDirectory().concat(fileDialog.getFile());

                        } catch (NullPointerException e) {
                            this.logger.warning("You just didn't save png");
                        }
                        break;
                    }
                }
            });
            thread.start();
        });
        fileDialog.setFilenameFilter((dir, name) -> name.endsWith(".png"));
        saveProjectFileDialog.setFilenameFilter((dir, name) -> name.endsWith(".json"));
        configPanel.add(ImportPngButton);
        saveProjectButton.setBounds(0,configPanel.getHeight()-180,300,30);
        ImportPngButton.setBounds(0,this.getHeight()-150,300,30);


        this.saveProjectButton.addActionListener(event->{
            saveProjectFileDialog.setVisible(true);
            Thread thread = new Thread(()->{
                while(true){
                    try{
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        logger.warning("wait error");
                    }
                    if (!saveProjectFileDialog.isVisible()) {
                        try {
                            FileWriter fileWriter = new FileWriter(saveProjectFileDialog.getDirectory().concat(saveProjectFileDialog.getFile()));
                            ArrayList<SlotInformation> slotsIM = new ArrayList<>();
                            for(SlotLabel slot:slots){
                                slotsIM.add(slot.getSlotInformation());
                            }
                            ArrayList<SlotGroupInformation> slotGroupsIM = new ArrayList<>(this.slotGroups);
                            SimpleMenuProject simpleMenuProject  =new SimpleMenuProject(
                                    project_name,mainImagePath,
                                    new SlotManager(slotsIM),
                                    version,false,new SlotGroupManage(slotGroupsIM));
                            fileWriter.append(simpleMenuProject.toJson().toJSONString());
                            fileWriter.close();
                        } catch (IOException e) {
                            logger.warning("Can't write Project file");
                        }
                        break;
                    }
                }
            });
            thread.start();
        });
        this.removeSlotButton.setBounds(150,0,150,30);
        this.removeSlotButton.setText(lang.getContent("remove_slot_button"));
        this.addNewGuiButton.setBounds(0,0,150,30);
        this.configPanel.add(this.addNewGuiButton);
        this.slotTip.setBounds(0,30,300,30);
        this.slotTip.setText(lang.getContent("slot_tip"));
        this.configPanel.add(slotTip);
        this.addNewGuiButton.setText(lang.getContent("add_new_slot_button"));
        this.inputIndexSlot.setBounds(0,60,300,30);
        this.rightSlotButton.setBounds(0,90,300,30);
        this.rightSlotButton.setText(lang.getContent("right_slot_button"));
        this.configPanel.add(this.inputIndexSlot);
        this.rightSlotButton.addActionListener(event->{
            try{
                NowIndex = Integer.parseInt(inputIndexSlot.getText());
                if(this.slotTypeChoose.getSelectedItem() == SlotType.DefaultSlot){
                    SlotLabel slot = getSlot(NowIndex);
                    this.inputSlotY.setText(String.valueOf(slot.getSlotY()));
                    this.inputSlotX.setText(String.valueOf(slot.getSlotX()));
                    this.logger.info("right Default Slot"+NowIndex+" is ok");
                    System.out.println(slot.getType().toString()+"d");
                }else if(this.slotTypeChoose.getSelectedItem()==SlotType.Inventory){
                    SlotLabel slot = getInventorySlot(NowIndex);
                    this.inputSlotY.setText(String.valueOf(slot.getSlotY()));
                    this.inputSlotX.setText(String.valueOf(slot.getSlotX()));
                    this.logger.info("right Inventory Slot"+NowIndex+" is ok");
                    System.out.println(slot.getType().toString()+"i");
                }else if(this.slotTypeChoose.getSelectedItem()==SlotType.OutPutSlot){
                    SlotLabel slot = getOutputSlot(NowIndex);
                    this.inputSlotY.setText(String.valueOf(slot.getSlotY()));
                    this.inputSlotX.setText(String.valueOf(slot.getSlotX()));
                    this.logger.info("right Output Slot"+NowIndex+" is ok");
                    System.out.println(slot.getType().toString()+"o");
                }
            } catch (Exception e) {
                logger.warning("the you write is not integer or the number is very big");
            }
        });
        this.configPanel.add(rightSlotButton);
        this.removeSlotButton.addActionListener(event->{
            if(this.slotTypeChoose.getSelectedItem() == SlotType.DefaultSlot){
                removeSlot(NowIndex);
            }else if(this.slotTypeChoose.getSelectedItem() == SlotType.OutPutSlot){
                removeOutputSlot(NowIndex);
            }else if(this.slotTypeChoose.getSelectedItem() == SlotType.Inventory){
                removeInventorySlot(NowIndex);
            }
        });
        this.addNewGuiButton.addActionListener(event->{
            if(slotTypeChoose.getSelectedItem() == SlotType.DefaultSlot){
                if(this.removeSlotIndex.isEmpty()) {
                        addSlot(new SimpleSlotInformation(0, 0, indexSlotAll, SlotType.DefaultSlot));
                        NowIndex = indexSlotAll;
                        inputIndexSlot.setText(String.valueOf(NowIndex));
                        this.inputSlotY.setText(String.valueOf(0));
                        this.inputSlotX.setText(String.valueOf(0));
                        ++indexSlotAll;
                }else {
                    for(int index:this.removeSlotIndex){
                            addSlot(new SimpleSlotInformation(0, 0, index, SlotType.DefaultSlot));
                            NowIndex = index;
                            inputIndexSlot.setText(String.valueOf(NowIndex));
                            this.inputSlotY.setText(String.valueOf(0));
                            this.inputSlotX.setText(String.valueOf(0));
                            ++indexSlotAll;
                            break;
                    }
                }
            }else if(slotTypeChoose.getSelectedItem()==SlotType.Inventory){
                if(this.removeOutputIndex.isEmpty()){
                    {
                        if (indexSlotAllInventory <= 35) {
                            addInventorySlot(new SimpleSlotInformation(0, 0, indexSlotAllInventory, SlotType.Inventory));
                            NowIndex = indexSlotAllInventory;
                            inputIndexSlot.setText(String.valueOf(NowIndex));
                            this.inputSlotY.setText(String.valueOf(0));
                            this.inputSlotX.setText(String.valueOf(0));
                            ++indexSlotAllInventory;
                        }
                    }
                }else {
                    for(int index:this.removeOutputIndex) {
                            addInventorySlot(new SimpleSlotInformation(0, 0, index, SlotType.Inventory));
                            NowIndex = index;
                            inputIndexSlot.setText(String.valueOf(NowIndex));
                            this.inputSlotY.setText(String.valueOf(0));
                            this.inputSlotX.setText(String.valueOf(0));
                            ++indexSlotAllInventory;
                            break;
                    }
                }
            }else if(slotTypeChoose.getSelectedItem()==SlotType.OutPutSlot){
                if(this.removeInventoryIndex.isEmpty()){
                    addOutput(new SimpleSlotInformation(0, 0, outputIndexAll,SlotType.OutPutSlot));
                    NowIndex = indexSlotAllInventory;
                    inputIndexSlot.setText(String.valueOf(NowIndex));
                    this.inputSlotY.setText(String.valueOf(0));
                    this.inputSlotX.setText(String.valueOf(0));
                    ++outputIndexAll;
                }else {
                    for(int index:this.removeInventoryIndex){
                        addOutput(new SimpleSlotInformation(0, 0,index,SlotType.OutPutSlot));
                        NowIndex = index;
                        inputIndexSlot.setText(String.valueOf(NowIndex));
                        this.inputSlotY.setText(String.valueOf(0));
                        this.inputSlotX.setText(String.valueOf(0));
                        ++outputIndexAll;
                        break;
                    }
                }
            }
        });
        this.SynchronousSlotButton.setBounds(0,300,300,30);
        this.SynchronousSlotButton.addActionListener(event-> {
            SynchronousSlotJDialog window = new SynchronousSlotJDialog(this);
            window.setVisible(true);
        });
        this.inputSlotX.setBounds(30,120,120,30);
        this.inputSlotY.setBounds(180,120,120,30);
        JLabel XLabel = new JLabel(lang.getContent("X:"),JLabel.CENTER);
        JLabel YLabel =new JLabel("Y:",JLabel.CENTER);
        XLabel.setBounds(0,120,30,30);
        YLabel.setBounds(150,120,30,30);
        YLabel.setForeground(Color.WHITE);
        XLabel.setForeground(Color.WHITE);
        this.rightLocationButton.setText(lang.getContent("right_location_button"));
        this.rightLocationButton.setBounds(0,150,300,30);
        this.rightLocationButton.addActionListener(event->{
            if(slotTypeChoose.getSelectedItem() == SlotType.Inventory){
                SlotLabel nowLabel = getInventorySlot(NowIndex);
                nowLabel.newSetLocation(Integer.parseInt(inputSlotX.getText()), Integer.parseInt(inputSlotY.getText()));
                this.logger.info("right Inventory Slot location is ok");
            }else if(slotTypeChoose.getSelectedItem()==SlotType.DefaultSlot){
                SlotLabel nowLabel = getSlot(NowIndex);
                nowLabel.newSetLocation(Integer.parseInt(inputSlotX.getText()), Integer.parseInt(inputSlotY.getText()));
                this.logger.info("right Default Slot location is ok");
            }else if(slotTypeChoose.getSelectedItem() ==SlotType.OutPutSlot){
                SlotLabel nowLabel = getOutputSlot(NowIndex);
                nowLabel.newSetLocation(Integer.parseInt(inputSlotX.getText()), Integer.parseInt(inputSlotY.getText()));
                this.logger.info("right Output Slot location is ok");
            }
        });
        this.slotTip.setForeground(Color.WHITE);
        this.setBounds(new Rectangle(new Point(0,0),Toolkit.getDefaultToolkit().getScreenSize()));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.exportJavaFile.setText(lang.getContent("export_java_file_button"));
        this.exportJavaFile.setBounds(0,this.configPanel.getHeight()-120,300,30);
        FileDialog YourWillExportJavaFile = new FileDialog(new Frame(),lang.getContent("export_java_file_button"),1);
        this.exportJavaFile.addActionListener(event->{
            YourWillExportJavaFile.setVisible(true);
            Thread thread = new Thread(()->{
                while(true){
                    try{
                        TimeUnit.SECONDS.sleep(1);
                    }catch (InterruptedException e){
                        logger.warning("sleep Error");
                    }
                    if(!YourWillExportJavaFile.isVisible()){
                        MethodSpec quickMoveStackMethod = MethodSpec.methodBuilder("quickMoveStack")
                                .addAnnotation(Override.class)
                                .addModifiers(Modifier.PUBLIC)
                                .returns(ClassName.get("net.minecraft.world.item","ItemStack"))
                                .addParameter(ClassName.get("net.minecraft.world.entity.player","Player"),"player")
                                .addParameter(int.class,"index")
                                .addStatement("return ItemStack.EMPTY")
                                .build();
                        FieldSpec ContainerSlot = FieldSpec.builder(ClassName.get("net.minecraft.world","SimpleContainer"),"container")
                                .addModifiers(Modifier.FINAL)
                                .addModifiers(Modifier.PRIVATE)
                                .initializer("new SimpleContainer($L)",this.indexSlotAll+1)
                                .build();
                        MethodSpec stillValidMethod = MethodSpec.methodBuilder("stillValid")
                                .returns(boolean.class)
                                .addModifiers(Modifier.PUBLIC)
                                .addAnnotation(Override.class)
                                .addParameter(ClassName.get("net.minecraft.world.entity.player","Player"),"player")
                                .addStatement("return true")
                                .build();
                        FieldSpec texturesX = FieldSpec.builder(int.class,"TexturesX")
                                .initializer("0")
                                .addModifiers(Modifier.FINAL)
                                .addModifiers(Modifier.PRIVATE)
                                .addModifiers(Modifier.STATIC)
                                .build();

                        FieldSpec texturesY = FieldSpec.builder(int.class,"TexturesY")
                                .initializer("0")
                                .addModifiers(Modifier.FINAL)
                                .addModifiers(Modifier.PRIVATE)
                                .addModifiers(Modifier.STATIC)
                                .build();
                        MethodSpec.Builder projectNameMethodBuilder = MethodSpec.constructorBuilder()
                                .addModifiers(Modifier.PUBLIC)
                                .addParameter(int.class,"id")
                                .addParameter(ClassName.get("net.minecraft.world.entity.player","Inventory"),"inventory")
                                .addStatement("super(null,id)");
                        for(SlotLabel slotLabel:slots){
                            if(slotLabel.getType() == SlotType.DefaultSlot)projectNameMethodBuilder.addStatement("this.addSlot(new Slot(container,$L,$L+TexturesX,$L+TexturesY))",slotLabel.getIndex(),slotLabel.getSlotX(), slotLabel.getSlotY());
                        }
                        for(SlotLabel inventorySlotLabel:this.slots){
                            if(inventorySlotLabel.getType()== SlotType.Inventory) projectNameMethodBuilder.addStatement("this.addSlot(new Slot(inventory,$L,$L+TexturesX,$L+TexturesY))",inventorySlotLabel.getIndex(),inventorySlotLabel.getSlotX(),inventorySlotLabel.getSlotY());
                        }
                        for(SlotLabel slot:this.slots){
                           if(slot.getType() == SlotType.OutPutSlot) projectNameMethodBuilder.addStatement("this.addSlot(new OutputSlot(outputContainer,$L,$L+TexturesX,$L+TexturesY))",slot.getIndex(),slot.getSlotX(),slot.getSlotY());
                        }
                        FieldSpec outputContainer = FieldSpec.builder(ClassName.get("net.minecraft.world","SimpleContainer"),"outputContainer")
                                .addModifiers(Modifier.PRIVATE)
                                .addModifiers(Modifier.FINAL)
                                .initializer("new SimpleContainer($L)",outputIndexAll+1)
                                .build();
                        TypeSpec outPutSlot =  TypeSpec.classBuilder("OutputSlot")
                                .superclass(ClassName.get("net.minecraft.world.inventory","Slot"))
                                .addMethod(MethodSpec.methodBuilder("mayPlace")
                                        .addParameter(ClassName.get("net.minecraft.world.item","ItemStack"),"stack")
                                        .addStatement("return false")
                                        .returns(boolean.class)
                                        .addModifiers(Modifier.PUBLIC)
                                        .addAnnotation(Override.class)
                                        .build())
                                .addMethod(MethodSpec.constructorBuilder()
                                        .addParameter(ClassName.get("net.minecraft.world","Container"),"container")
                                        .addParameter(int.class,"index")
                                        .addParameter(int.class,"x")
                                        .addParameter(int.class,"y")
                                        .addStatement("super(container,index,x,y)")
                                        .build())
                                .addModifiers(Modifier.PUBLIC)
                                .addModifiers(Modifier.FINAL)
                                .addModifiers(Modifier.STATIC)
                                .build();
                        TypeSpec.Builder YourAbstractContainerMenuClassBuilder= TypeSpec.classBuilder(project_name)
                                .superclass(ClassName.get("net.minecraft.world.inventory","AbstractContainerMenu"))
                                .addModifiers(Modifier.PUBLIC)
                                .addMethod(quickMoveStackMethod)
                                .addMethod(stillValidMethod)
                                .addField(ContainerSlot)
                                .addField(texturesX)
                                .addField(texturesY)
                                .addField(outputContainer)
                                .addType(outPutSlot);
                        int containerNumber = 0;
                        for(SlotGroupInformation information:this.slotGroups){
                            YourAbstractContainerMenuClassBuilder.addField(FieldSpec.builder(ClassName.get("net.minecraft.world","SimpleContainer"),"containerSlotGroup"+containerNumber)
                                            .addModifiers(Modifier.PRIVATE)
                                            .addModifiers(Modifier.FINAL)
                                            .initializer("new SimpleContainer($L)",information.getWeightSlot()*information.getHeightSlot()).build());
                                    projectNameMethodBuilder.addCode("for(int x=0;x<$L;++x){\nfor(int y=0;y<$L;++y){\nthis.addSlot(new Slot(containerSlotGroup"+containerNumber+",$L+x*y+x+TexturesX,$L+x*18+TexturesY,y*18));\n}\n}",information.getHeightSlot(),information.getWeightSlot(),information.getWeightSlot(),information.getHeightSlot());
                            ++containerNumber;
                        }
                        MethodSpec projectNameMethod = projectNameMethodBuilder.build();
                        YourAbstractContainerMenuClassBuilder.addMethod(projectNameMethod);
                        TypeSpec YourAbstractContainerMenuClass = YourAbstractContainerMenuClassBuilder.build();
                        JavaFile javaFile = JavaFile.builder("minecraft_ui_design_endYingJie",YourAbstractContainerMenuClass)
                                .build();
                        try{
                            javaFile.writeTo(new File(YourWillExportJavaFile.getDirectory().concat(YourWillExportJavaFile.getFile())));
                        } catch (IOException e) {
                            logger.warning("Can't write your java file");
                        }
                    }
                }
            });
            thread.start();
        });
        this.DetailTweaksOpen.setBounds(0,210,300,30);
        this.DetailTweaksOpen.setText(lang.getContent("detail_tweaks_open_button"));
        this.DetailTweaksOpen.addActionListener(event->{
            DetailTweaksJDialog detailTweaksWindow = new DetailTweaksJDialog(lang.getContent("detail_tweaks_open_button"),this);
        });
        this.SlotGroupConfigurationButton.setBounds(0,270,300,30);
        this.SlotGroupConfigurationButton.setText(lang.getContent("slot_group_configuration_button"));
        this.SlotGroupConfigurationButton.addActionListener(event->{
            SlotGroupConfigurationJDialog window =new SlotGroupConfigurationJDialog(this);
            window.setVisible(true);
        });
        this.configPanel.add(SlotGroupConfigurationButton);
        this.setMinimumSize(new Dimension(1000,600));
        this.copySlotButton.setText(lang.getContent("copy_slot_button"));
        this.copySlotButton.addActionListener(event->{
            if(slotTypeChoose.getSelectedItem() == SlotType.DefaultSlot){
                SlotLabel slot =addSlot(new SimpleSlotInformation(0, 0, indexSlotAll,SlotType.DefaultSlot));
                int justNowIndex = NowIndex;
                SlotLabel slot2 = getSlot(justNowIndex);
                NowIndex = indexSlotAll;
                inputIndexSlot.setText(String.valueOf(NowIndex));
                this.inputSlotY.setText(String.valueOf(slot2.getSlotY()));
                this.inputSlotX.setText(String.valueOf(slot2.getSlotX()));
                ++indexSlotAll;
                slot.newSetLocation(slot2.getSlotX(),slot2.getSlotY());

            }else if(slotTypeChoose.getSelectedItem()==SlotType.Inventory){
                if(indexSlotAllInventory<=36){
                    SlotLabel slot =  addInventorySlot(new SimpleSlotInformation(0, 0, indexSlotAllInventory, SlotType.OutPutSlot));
                    int justNowIndex  = NowIndex;
                    SlotLabel inventorySlot = getInventorySlot(justNowIndex);
                    NowIndex = indexSlotAllInventory;
                    inputIndexSlot.setText(String.valueOf(NowIndex));
                    this.inputSlotY.setText(String.valueOf(inventorySlot.getSlotY()));
                    this.inputSlotX.setText(String.valueOf(inventorySlot.getSlotX()));
                    ++indexSlotAllInventory;
                    slot.newSetLocation(inventorySlot.getSlotX(),inventorySlot.getSlotY());
                }
            }else if(slotTypeChoose.getSelectedItem()==SlotType.OutPutSlot){
                SlotLabel outputSlot= addOutput(new SimpleSlotInformation(0, 0,outputIndexAll,SlotType.OutPutSlot));
                int justNowIndex  =NowIndex;
                SlotLabel outputSlot2 =getOutputSlot(justNowIndex);
                NowIndex = indexSlotAllInventory;
                inputIndexSlot.setText(String.valueOf(NowIndex));
                this.inputSlotY.setText(String.valueOf(outputSlot2.getSlotY()));
                this.inputSlotX.setText(String.valueOf(outputSlot2.getSlotX()));
                ++outputIndexAll;
                outputSlot.newSetLocation(outputSlot2.getSlotX(),outputSlot2.getSlotY());
            }
        });
        this.setIconImage(ResourceImageIcon.create("icon.png").getImage().getImage());
        this.copySlotButton.setBounds(0,240,300,30);
        this.configPanel.add(copySlotButton);
        this.configPanel.add(this.DetailTweaksOpen);
        this.configPanel.add(exportJavaFile);
        this.configPanel.add(rightLocationButton);
        this.configPanel.add(YLabel);
        this.configPanel.add(XLabel);
        this.configPanel.add(SynchronousSlotButton);
        this.configPanel.add(this.inputSlotX);
        this.configPanel.add(this.inputSlotY);
        MainPaintingArea.setBounds(((this.getWidth()-300)-this.getHeight())/2,0,this.getHeight(),this.getHeight());
        this.configPanel.add(saveProjectButton);
        this.configPanel.add(removeSlotButton);
        this.saveProjectButton.setText(lang.getContent("save_project_button"));
        this.mainLabel.setBounds(new Rectangle(new Point(0,0),MainPaintingArea.getSize()));
        this.MainPaintingArea.add(this.mainLabel,new Integer(0));
        this.add(configPanel);
        if(isoVisible){
            loadingWindow.setVisible(false);
            this.setVisible(true);
        }
    }
    public SlotGroupLabel addSlotGroup(SlotGroupInformation slotGroup){
        SlotGroupLabel slotGroupLabel = new SlotGroupLabel(slotGroup,this);
        this.MainPaintingArea.add(slotGroupLabel,new Integer(1));
        this.slotGroupSet.add(slotGroupLabel::locationRepaint);
        this.slotGroupSet.add(slotGroupLabel::sizeRepaint);
        this.slotGroups.add(slotGroupLabel);
        this.MainPaintingArea.repaint();
        slotGroupLabel.sizeRepaint();
        slotGroupLabel.repaint();
        return slotGroupLabel;
    }
    public SlotGroupLabel getSlotGroupLabel(int index){
        for(SlotGroupLabel slotGroupLabel:this.slotGroups){
            if(slotGroupLabel.getIndex() == index){
                return slotGroupLabel;
            }
        }
        return null;
    }
    public void removeSlotGroup(int index){
        for(SlotGroupLabel slotGroupLabel:this.slotGroups){
            if(slotGroupLabel.getIndex() == index){
                this.MainPaintingArea.remove(slotGroupLabel);
                this.slotGroups.remove(slotGroupLabel);
                this.MainPaintingArea.repaint();
            }
        }
    }
    public double getOnePixel(){
        return this.OnePixel;
    }
    public SlotLabel addSlot(SlotInformation slot){
        SlotLabel slot1 = new SlotLabel(slot.getIndex(),SlotType.DefaultSlot,this);
        slot1.newSetLocation(slot.getSlotX(),slot.getSlotY());
        this.MainPaintingArea.add(slot1,new Integer(1));
        this.slotsSet.add(()->slot1.newSetLocation(slot1.getSlotX(),slot1.getSlotY()));
        this.slotsSet.add(()->slot1.setSize((int)this.getOnePixel()*18,(int)this.getOnePixel()*18));
        this.slots.add(slot1);
        this.inputSlotY.setText(String.valueOf(0));
        this.inputSlotX.setText(String.valueOf(0));
        this.MainPaintingArea.repaint();
        slot1.repaint();
        return slot1;
    }


    public SlotLabel addOutput(SlotInformation slot){
        SlotLabel slot1 = new SlotLabel(slot.getIndex(),SlotType.OutPutSlot,this);
        slot1.newSetLocation(slot.getSlotX(),slot.getSlotY());
        this.MainPaintingArea.add(slot1,new Integer(1));
        this.slotsSet.add(()->slot1.newSetLocation(slot1.getSlotX(),slot1.getSlotY()));
        this.slotsSet.add(()->slot1.setSize((int)this.getOnePixel()*18,(int)this.getOnePixel()*18));
        this.slots.add(slot1);
        this.MainPaintingArea.repaint();
        slot1.repaint();
        return slot1;
    }

    public SlotLabel addInventorySlot(SlotInformation slot){
        SlotLabel slot1 = new SlotLabel(slot.getIndex(),SlotType.Inventory,this);
        slot1.newSetLocation(slot.getSlotX(),slot.getSlotY());
        this.MainPaintingArea.add(slot1,new Integer(2));
        this.slotsSet.add(()->slot1.newSetLocation(slot1.getSlotX(),slot1.getSlotY()));
        this.slotsSet.add(()->slot1.setSize((int)this.getOnePixel()*18,(int)this.getOnePixel()*18));
        this.slots.add(slot1);
        this.MainPaintingArea.repaint();
        slot1.repaint();
        return slot1;
    }
    public MenuProjectWindow(MenuProject project){
        this(project.getName(),project.getVersion(),false);
        OnePixel =((double)MainPaintingArea.getHeight())/256.0;
        ImageIcon image = new ImageIcon(project.getImagePath());
        if(!project.isModel()){
            this.main_image = new ImageIcon(image.getImage().getScaledInstance(this.MainPaintingArea.getHeight(), this.MainPaintingArea.getHeight(), Image.SCALE_SMOOTH));
        }else this.main_image = ResourceImageIcon.create("model/".concat(project.getImagePath())).getImage();
        this.mainImagePath = project.getImagePath();
        this.mainLabel.setIcon(this.main_image);
        this.setVisible(true);
        int index = 0;
        for(SlotInformation slot:project.getSlotManager().getSlots()) {
            if(slot.getType()==SlotType.DefaultSlot){
                SlotLabel Slot = SlotLabel.of(slot, this);
                this.addSlot(Slot);
                index = Math.max(index, slot.getIndex());
            }
        }
        int inventoryIndex =0;
        for(SlotInformation slot:project.getSlotManager().getSlots()) {
            if(slot.getType() == SlotType.Inventory){
                SlotLabel inventorySlot = SlotLabel.of(slot, this);
                this.addInventorySlot(inventorySlot);
                inventoryIndex = Math.max(inventoryIndex, slot.getIndex());
            }
        }
        int outputIndex =0;
        for(SlotInformation slot:project.getSlotManager().getSlots()) {
            if(slot.getType() == SlotType.OutPutSlot){
                SlotLabel outputSlot = SlotLabel.of(slot, this);
                this.addOutput(outputSlot);
                outputIndex = Math.max(outputIndex, slot.getIndex());
            }
        }
        int slotGroupIndex = 0;
        for(SlotGroupInformation slotGroup :project.getSlotGroups().getSlots()){
            this.addSlotGroup(slotGroup);
            slotGroupIndex =Math.max(slotGroupIndex,slotGroup.getIndex());
        }
        this.indexSlotAll = index;
        this.indexSlotAllInventory = inventoryIndex;
        this.outputIndexAll = outputIndex;
        this.indexSlotGroupAll = slotGroupIndex;
        loadingWindow.setVisible(false);
        this.setVisible(true);
    }
    public void removeOutputSlot(int index){
        for(SlotLabel outputSlot:this.slots){
            if(outputSlot.getType() == SlotType.OutPutSlot){
                if (index == outputSlot.getIndex()) {
                    this.MainPaintingArea.remove(outputSlot);
                    this.slots.remove(outputSlot);
                    this.MainPaintingArea.repaint();
                    this.outputIndexAll -= 1;
                    this.removeOutputIndex.add(index);
                    break;
                }
            }
        }
    }

    public void removeInventorySlot(int index){
        for(SlotLabel inventorySlot:this.slots){
            if(inventorySlot.getType() == SlotType.Inventory){
                if (index == inventorySlot.getIndex()) {
                    this.MainPaintingArea.remove(inventorySlot);
                    this.slots.remove(inventorySlot);
                    this.MainPaintingArea.repaint();
                    this.indexSlotAllInventory -= 1;
                    this.removeInventoryIndex.add(index);
                    break;
                }
            }
        }
    }

    private static final class SynchronousSlotJDialog extends JDialog{
        private static final Color background = new Color(0x878686);
        private final PersonalizedTextField inputSlotOne = new PersonalizedTextField("0");
        private final PersonalizedTextField inputSlotTwo = new PersonalizedTextField("0");
        private int slotsFirst=0;
        private int slotsLast=0;
        private boolean slotsEmpty = false;
        private final JComboBox<SlotType> slotTypeChoose  = new JComboBox<>();
        private final PersonalizedButton rightSlotsButton = new PersonalizedButton(lang.getContent("right_slots_button"));
        private static final JLabel xTip = new JLabel("X:",JLabel.CENTER);
        private static final JLabel yTip = new JLabel("Y:",JLabel.CENTER);
        private final PersonalizedTextField inputX = new PersonalizedTextField("0");
        private final PersonalizedTextField inputY = new PersonalizedTextField("0");
        private final PersonalizedButton copySlotsButton = new PersonalizedButton(lang.getContent("copy_slots_button"));
        private final PersonalizedButton rightLocationButton = new PersonalizedButton(lang.getContent("right_location_slots_button"));
        private static final JLabel indexTip = new JLabel(lang.getContent("index_tip"),JLabel.CENTER);
        private ArrayList<SlotLabel> slots = new ArrayList<>();
        static{
            xTip.setBounds(0,90,30,30);
            yTip.setBounds(150,90,30,30);
            xTip.setForeground(Color.WHITE);
            yTip.setForeground(Color.WHITE);
            indexTip.setBounds(0,0,60,30);
            indexTip.setForeground(Color.WHITE);
        }
        public SynchronousSlotJDialog(MenuProjectWindow window){
            super(window,lang.getContent("synchronous_slot_window"),false);
            this.setBounds(Config.screenSize.width/2-150,Config.screenSize.height/2-200,300,500);
            this.setLayout(null);
            this.getContentPane().setBackground(background);
            this.inputSlotOne.setBounds(60,0,120,30);
            this.inputSlotTwo.setBounds(180,0,120,30);
            this.rightSlotsButton.setBounds(0,60,300,30);
            this.slotTypeChoose.setBounds(0,30,300,30);
            this.slotTypeChoose.setBackground(new Color(0x3D3D3D));
            this.slotTypeChoose.setForeground(Color.WHITE);
            for(SlotType type:SlotType.values()){
                slotTypeChoose.addItem(type);
            }
            this.rightSlotsButton.addActionListener(event->{
                if(Integer.parseInt(inputSlotOne.getText())>=0&&Integer.parseInt(inputSlotTwo.getText())>=0){
                    if(Integer.parseInt(inputSlotTwo.getText())<Integer.parseInt(inputSlotOne.getText())){
                        this.slotsFirst = Integer.parseInt(inputSlotTwo.getText());
                        this.slotsLast= Integer.parseInt(inputSlotOne.getText());
                    }else if(Integer.parseInt(inputSlotTwo.getText())>Integer.parseInt(inputSlotOne.getText())){
                        this.slotsFirst = Integer.parseInt(inputSlotOne.getText());
                        this.slotsLast= Integer.parseInt(inputSlotTwo.getText());
                    }else {
                        this.slotsFirst = 0;
                        this.slotsLast= 0;
                        this.slotsEmpty = true;
                    }
                }else window.logger.warning("Please write more than 0 number");
                if(!slotsEmpty){
                    ArrayList<SlotLabel> slots2 = new ArrayList<>();
                    SlotType type = (SlotType)this.slotTypeChoose.getSelectedItem();
                    for(int i= slotsFirst;i<=slotsLast;i++){
                        System.out.println("hello");
                        try{
                           switch (type){
                               case DefaultSlot:
                                   if(window.getSlot(i)!=null) {
                                       slots2.add(window.getSlot(i));
                                       window.logger.info("Default Slot"+i+" is right");
                                   }
                                   else window.logger.warning("not find Default Slot");
                                   break;
                               case OutPutSlot:
                                   if(window.getOutputSlot(i)!=null) {
                                       slots2.add(window.getOutputSlot(i));
                                       window.logger.info("Output Slot"+i+" is right");
                                   }
                                   else window.logger.warning("not find Output Slot");
                                   break;
                               case Inventory:
                                   if(window.getInventorySlot(i)!=null) {
                                       slots2.add(window.getInventorySlot(i));
                                       window.logger.info("Inventory"+i+" is right");
                                   }else window.logger.warning("not find Inventory Slot");
                                   break;
                               default:
                                   if(window.getSlot(i)!=null) {
                                       slots2.add(window.getSlot(i));
                                       window.logger.info("Default Slot"+i+" is right");
                                   }
                                   else window.logger.warning("not find Default Slot");
                           }
                        } catch (NullPointerException e) {
                            window.logger.warning("not find slots");
                            break;
                        }
                    }
                    this.slots = slots2;
                }

            });
            this.inputX.setBounds(30,90,120,30);
            this.inputY.setBounds(180,90,120,30);
            this.rightLocationButton.addActionListener(event->{
                try{
                    for (SlotLabel slot : slots) {
                        slot.newSetLocation(slot.getSlotX() + Integer.parseInt(this.inputX.getText()), slot.getSlotY() + Integer.parseInt(this.inputY.getText()));
                        window.logger.info("right slot"+slot.getIndex()+" location is ok");
                    }
                    this.inputX.setText("0");
                    this.inputY.setText("0");
                } catch (NumberFormatException e) {
                    window.logger.warning("Is not number");
                }
            });
            copySlotsButton.addActionListener(event->{
                SlotType type = slots.get(0).getType();
                int index = 0;
                for(SlotLabel slot :slots){
                    switch (type){
                        case DefaultSlot:
                            ++index;
                            window.addSlot(new SimpleSlotInformation(slot.getSlotX(),slot.getSlotY(),slots.get(slots.size()-1).getIndex()+index,slot.getType()));
                            window.logger.info("copy slot is right"+index);
                            break;
                        case OutPutSlot:
                            ++index;
                            window.addOutput(new SimpleSlotInformation(slot.getSlotX(),slot.getSlotY(),slots.get(slots.size()-1).getIndex()+index,slot.getType()));
                            window.logger.info("copy slot is right"+index);
                            break;
                        case Inventory:
                            ++index;
                            window.addInventorySlot(new SimpleSlotInformation(slot.getSlotX(),slot.getSlotY(),slots.get(slots.size()-1).getIndex()+index,slot.getType()));
                            window.logger.info("copy slot is right"+index);
                            break;
                    }
                }
                this.inputSlotOne.setText(String.valueOf(slots.get(slots.size()-1).getIndex()+1));
                this.inputSlotTwo.setText(String.valueOf(index+slots.get(slots.size()-1).getIndex()));
                this.slotsFirst = slots.get(slots.size()-1).getIndex()+1;
                this.slotsLast= index+slots.get(slots.size()-1).getIndex();
                if(!slotsEmpty){
                    ArrayList<SlotLabel> slots2 = new ArrayList<>();
                    SlotType type2 = (SlotType)this.slotTypeChoose.getSelectedItem();
                    for(int i= slotsFirst;i<=slotsLast;i++){
                        try{
                            switch (type2){
                                case DefaultSlot:
                                    slots2.add(window.getSlot(i));
                                    window.logger.info("Default Slot is right");
                                    break;
                                case OutPutSlot:
                                    slots2.add(window.getOutputSlot(i));
                                    window.logger.info("Output Slot is right");
                                    break;
                                case Inventory:
                                    slots2.add(window.getInventorySlot(i));
                                    window.logger.info("Inventory is right");
                                    break;
                            }
                        } catch (NullPointerException e) {
                            window.logger.warning("not find slots");
                            break;
                        }
                    }
                    this.slots = slots2;
                }
            });
            this.copySlotsButton.setBounds(0,150,300,30);
            this.add(indexTip);
            this.add(copySlotsButton);
            this.add(rightSlotsButton);
            this.rightLocationButton.setBounds(0,120,300,30);
            this.add(rightLocationButton);
            this.add(inputX);
            this.add(inputY);
            this.add(xTip);
            this.add(yTip);
            this.add(slotTypeChoose);
            this.add(inputSlotOne);
            this.add(inputSlotTwo);
            this.setResizable(false);
            this.addWindowListener(new WindowListener() {
                @Override
                public void windowOpened(WindowEvent e) {

                }

                @Override
                public void windowClosing(WindowEvent e) {
                    slots = new ArrayList<>();
                }

                @Override
                public void windowClosed(WindowEvent e) {

                }

                @Override
                public void windowIconified(WindowEvent e) {

                }

                @Override
                public void windowDeiconified(WindowEvent e) {

                }

                @Override
                public void windowActivated(WindowEvent e) {

                }

                @Override
                public void windowDeactivated(WindowEvent e) {

                }
            });
        }
    }
    private static final class SlotGroupConfigurationJDialog extends JDialog{
        private static final Color background = new Color(0x888888);
        private final PersonalizedButton addNewSlotGroupButton  =new PersonalizedButton();
        private final PersonalizedTextField inputSlotGroupWeight = new PersonalizedTextField("0");
        private final PersonalizedTextField inputSlotGroupHeight = new PersonalizedTextField("0");
        private static final JLabel WeightTip = new JLabel("W:",0);
        private static final JLabel HeightTip = new JLabel("H:",0);
        private final PersonalizedButton rightSlotGroup  = new PersonalizedButton();
        private final PersonalizedTextField inputSlotGroupX = new PersonalizedTextField();
        private final PersonalizedTextField inputSlotGroupY = new PersonalizedTextField();
        private static final JLabel XTip = new JLabel("X:",0);
        private static final JLabel YTip = new JLabel("Y:",0);
        private final PersonalizedTextField  inputSlotGroupIndex= new PersonalizedTextField();
        private final PersonalizedButton removeSlotGroupButton = new PersonalizedButton();
        private final PersonalizedButton rightSlotGroupLocation = new PersonalizedButton();
        static{
            WeightTip.setBounds(0,30,30,30);
            HeightTip.setBounds(200,30,30,30);
            WeightTip.setForeground(Color.WHITE);
            HeightTip.setForeground(Color.WHITE);
            XTip.setBounds(0,60,30,30);
            YTip.setBounds(200,60,30,30);
            XTip.setForeground(Color.WHITE);
            YTip.setForeground(Color.WHITE);
        }
        public SlotGroupConfigurationJDialog(MenuProjectWindow window){
            super(window,lang.getContent("slot_group_configuration_title"),false);
            this.getContentPane().setBackground(background);
            Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
            this.setLayout(null);
            this.inputSlotGroupWeight.setBounds(30,30,170,30);
            this.inputSlotGroupHeight.setBounds(230,30,170,30);

            this.setBounds(size.width/2-200,size.height/2 - 300,400,600);
            addNewSlotGroupButton.setText(lang.getContent("slot_group_configuration_button"));
            addNewSlotGroupButton.setBounds(0,0,200,30);
            addNewSlotGroupButton.addActionListener(event->{
                if(Integer.parseInt(inputSlotGroupWeight.getText())>1&&Integer.parseInt(inputSlotGroupHeight.getText())>1){
                    SimpleSlotGroup slotGroup =new SimpleSlotGroup(0, 0, Integer.parseInt(inputSlotGroupWeight.getText()), Integer.parseInt(inputSlotGroupHeight.getText()), window.indexSlotGroupAll);
                    window.addSlotGroup(slotGroup);
                    window.nowIndexSlotGroup = window.indexSlotGroupAll;
                    ++window.indexSlotGroupAll;
                }
            });
            this.rightSlotGroupLocation.setText(lang.getContent("right_slot_group_location_button"));
            this.rightSlotGroup.setText(lang.getContent("right_slot_group_button"));
            this.rightSlotGroup.addActionListener(event->{
                window.nowIndexSlotGroup = Integer.parseInt(inputSlotGroupIndex.getText());
                SlotGroupLabel slotGroupLabel = window.getSlotGroupLabel(window.nowIndexSlotGroup);
                this.inputSlotGroupX.setText(String.valueOf(slotGroupLabel.getCX()));
                this.inputSlotGroupY.setText(String.valueOf(slotGroupLabel.getCY()));
                this.inputSlotGroupWeight.setText(String.valueOf(slotGroupLabel.getWeightSlot()));
                this.inputSlotGroupHeight.setText(String.valueOf(slotGroupLabel.getHeightSlot()));
            });
            this.removeSlotGroupButton.addActionListener(event->{
                window.removeSlotGroup(window.nowIndexSlotGroup);
            });
            this.inputSlotGroupIndex.setBounds(0,120,400,30);
            this.add(inputSlotGroupIndex);
            this.removeSlotGroupButton.setText(lang.getContent("remove_slot_group_button"));
            this.removeSlotGroupButton.setBounds(200,0,200,30);
            this.inputSlotGroupY.setText("0");
            this.inputSlotGroupX.setText("0");
            this.add(XTip);
            this.add(YTip);
            this.rightSlotGroupLocation.setBounds(0,150,400,30);
            this.rightSlotGroupLocation.addActionListener(event->{
                SlotGroupLabel slotGroupLabel = window.getSlotGroupLabel(window.nowIndexSlotGroup);
                slotGroupLabel.newSetLocation(Integer.parseInt(inputSlotGroupX.getText()),Integer.parseInt(inputSlotGroupY.getText()));
                window.repaint();
            });
            this.add(removeSlotGroupButton);
            this.add(rightSlotGroupLocation);
            this.inputSlotGroupIndex.setBounds(0,90,400,30);
            this.inputSlotGroupX.setBounds(30,60,170,30);
            this.inputSlotGroupY.setBounds(230,60,170,30);
            this.rightSlotGroup.setBounds(0,120,400,30);
            this.setResizable(false);
            this.add(inputSlotGroupX);
            this.add(inputSlotGroupY);
            this.add(rightSlotGroup);
            this.add(WeightTip);
            this.add(HeightTip);
            this.add(inputSlotGroupWeight);
            this.add(inputSlotGroupHeight);
            this.add(addNewSlotGroupButton);
        }
    }
    private static final class DetailTweaksJDialog extends JDialog{
        private static final Color BackGroundColor = new Color(0x3D3D3D);
        private final PersonalizedButton MoveSlotDistanceUp = new PersonalizedButton();
        private final PersonalizedButton MoveSlotDistanceDown = new PersonalizedButton();
        private final PersonalizedButton MoveSlotDistanceLeft = new PersonalizedButton();
        private final PersonalizedButton MoveSlotDistanceRight = new PersonalizedButton();
        public DetailTweaksJDialog(String title,MenuProjectWindow window){
            super(window,title,false);
            this.getContentPane().setBackground(BackGroundColor);
            this.setLayout(null);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            this.setBounds(screenSize.width/2-200,screenSize.height/2-300,400,600);
            this.MoveSlotDistanceUp.addActionListener(event->{
                try{
                    if (window.slotTypeChoose.getSelectedItem() == SlotType.DefaultSlot) {
                        SlotLabel slot = window.getSlot(window.NowIndex);
                        slot.newSetLocation(slot.getSlotX(), slot.getSlotY() - 18);
                        window.inputSlotX.setText(String.valueOf(slot.getSlotX()));
                        window.inputSlotY.setText(String.valueOf(slot.getSlotY()));
                    } else if (window.slotTypeChoose.getSelectedItem() == SlotType.Inventory) {
                        SlotLabel slot = window.getInventorySlot(window.NowIndex);
                        slot.newSetLocation(slot.getSlotX(), slot.getSlotY() - 18);
                        window.inputSlotX.setText(String.valueOf(slot.getSlotX()));
                        window.inputSlotY.setText(String.valueOf(slot.getSlotY()));
                    } else if (window.slotTypeChoose.getSelectedItem() == SlotType.OutPutSlot) {
                        SlotLabel slot = window.getOutputSlot(window.NowIndex);
                        slot.newSetLocation(slot.getSlotX(), slot.getSlotY() - 18);
                        window.inputSlotX.setText(String.valueOf(slot.getSlotX()));
                        window.inputSlotY.setText(String.valueOf(slot.getSlotY()));
                    }
                } catch (Exception e) {
                    window.logger.warning("Don't found the slot");
                }
            });
            this.MoveSlotDistanceDown.addActionListener(event->{
                try{
                    if (window.slotTypeChoose.getSelectedItem() == SlotType.DefaultSlot) {
                        SlotLabel slot = window.getSlot(window.NowIndex);
                        slot.newSetLocation(slot.getSlotX(), slot.getSlotY() + 18);
                        window.inputSlotX.setText(String.valueOf(slot.getSlotX()));
                        window.inputSlotY.setText(String.valueOf(slot.getSlotY()));
                    } else if (window.slotTypeChoose.getSelectedItem() == SlotType.Inventory) {
                        SlotLabel slot = window.getInventorySlot(window.NowIndex);
                        slot.newSetLocation(slot.getSlotX(), slot.getSlotY() + 18);
                        window.inputSlotX.setText(String.valueOf(slot.getSlotX()));
                        window.inputSlotY.setText(String.valueOf(slot.getSlotY()));
                    } else if (window.slotTypeChoose.getSelectedItem() == SlotType.OutPutSlot) {
                        SlotLabel slot = window.getOutputSlot(window.NowIndex);
                        slot.newSetLocation(slot.getSlotX(), slot.getSlotY() + 18);
                        window.inputSlotX.setText(String.valueOf(slot.getSlotX()));
                        window.inputSlotY.setText(String.valueOf(slot.getSlotY()));
                    }
                } catch (Exception e) {
                    window.logger.warning("Don't found the slot");
                }
            });
            this.MoveSlotDistanceLeft.addActionListener(event->{
                try{
                    if (window.slotTypeChoose.getSelectedItem() == SlotType.DefaultSlot) {
                        SlotLabel slot = window.getSlot(window.NowIndex);
                        slot.newSetLocation(slot.getSlotX() - 18, slot.getSlotY());
                        window.inputSlotX.setText(String.valueOf(slot.getSlotX()));
                        window.inputSlotY.setText(String.valueOf(slot.getSlotY()));
                    } else if (window.slotTypeChoose.getSelectedItem() == SlotType.Inventory) {
                        SlotLabel slot = window.getInventorySlot(window.NowIndex);
                        slot.newSetLocation(slot.getSlotX() - 18, slot.getSlotY());
                        window.inputSlotX.setText(String.valueOf(slot.getSlotX()));
                        window.inputSlotY.setText(String.valueOf(slot.getSlotY()));
                    } else if (window.slotTypeChoose.getSelectedItem() == SlotType.OutPutSlot) {
                        SlotLabel slot = window.getOutputSlot(window.NowIndex);
                        slot.newSetLocation(slot.getSlotX() - 18, slot.getSlotY());
                        window.inputSlotX.setText(String.valueOf(slot.getSlotX()));
                        window.inputSlotY.setText(String.valueOf(slot.getSlotY()));
                    }
                } catch (Exception e) {
                    window.logger.warning("Don't found the slot");
                }
            });
            this.MoveSlotDistanceRight.addActionListener(event->{
                try{
                    if (window.slotTypeChoose.getSelectedItem() == SlotType.DefaultSlot) {
                        SlotLabel slot = window.getSlot(window.NowIndex);
                        slot.newSetLocation(slot.getSlotX() + 18, slot.getSlotY());
                        window.inputSlotX.setText(String.valueOf(slot.getSlotX()));
                        window.inputSlotY.setText(String.valueOf(slot.getSlotY()));
                    } else if (window.slotTypeChoose.getSelectedItem() == SlotType.Inventory) {
                        SlotLabel slot = window.getInventorySlot(window.NowIndex);
                        slot.newSetLocation(slot.getSlotX() + 18, slot.getSlotY());
                        window.inputSlotX.setText(String.valueOf(slot.getSlotX()));
                        window.inputSlotY.setText(String.valueOf(slot.getSlotY()));
                    } else if (window.slotTypeChoose.getSelectedItem() == SlotType.OutPutSlot) {
                        SlotLabel slot = window.getOutputSlot(window.NowIndex);
                        slot.newSetLocation(slot.getSlotX() + 18, slot.getSlotY());
                        window.inputSlotX.setText(String.valueOf(slot.getSlotX()));
                        window.inputSlotY.setText(String.valueOf(slot.getSlotY()));
                    }
                } catch (Exception e) {
                    window.logger.warning("Don't found the slot");
                }
            });
            this.MoveSlotDistanceUp.setText(lang.getContent("slot_one_up"));
            this.MoveSlotDistanceDown.setText(lang.getContent("slot_one_down"));
            this.MoveSlotDistanceLeft.setText(lang.getContent("slot_one_left"));
            this.MoveSlotDistanceRight.setText(lang.getContent("slot_one_right"));
            this.MoveSlotDistanceUp.setBounds(0,0,100,30);
            this.MoveSlotDistanceDown.setBounds(100,0,100,30);
            this.MoveSlotDistanceLeft.setBounds(200,0,100,30);
            this.MoveSlotDistanceRight.setBounds(300,0,100,30);
            this.add(MoveSlotDistanceUp);
            this.add(MoveSlotDistanceDown);
            this.add(MoveSlotDistanceLeft);
            this.add(MoveSlotDistanceRight);
            this.setResizable(false);
            this.setVisible(true);
        }
    }

    public SlotLabel getSlot(int index){
        for(SlotLabel slot:this.slots){
            if(slot.getType() == SlotType.DefaultSlot){
                if (index == slot.getIndex()) {
                    return slot;
                }
            }
        }
        return null;
    }
    public SlotLabel getOutputSlot(int index){
        for(SlotLabel slot:this.slots){
            if(slot.getType()== SlotType.OutPutSlot){
                if (index == slot.getIndex()) {
                    return slot;
                }
            }
        }
        return null;
    }

    public SlotLabel getInventorySlot(int index){
        for(SlotLabel slot:this.slots){
            if(slot.getType()==SlotType.Inventory){
                if (index == slot.getIndex()) {
                    return slot;
                }
            }
        }
        return null;
    }
    public void removeSlot(int index){
        for(SlotLabel slot:this.slots){
            if(index==slot.getIndex()){
                this.MainPaintingArea.remove(slot);
                this.slots.remove(slot);
                this.MainPaintingArea.repaint();
                this.indexSlotAll-=1;
                this.removeSlotIndex.add(index);
                break;
            }
        }
    }

    public Point getMainLabelLocation(){
       return this.mainLabel.getLocation();
    }
    public int getSlotIndexAll(){
        return this.indexSlotAll;
    }
    public int getMainImageWeight(){
        return this.main_image_weight;
    }
    public int getMainImageHeight(){
        return this.main_image_height;
    }
}
