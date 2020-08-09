/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */   
package main;
import java.awt.Color;
import java.awt.EventQueue; 
import java.util.ArrayList;
import javax.swing.JFrame; 
import javax.swing.UIManager;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import javax.swing.JPanel;
import javax.swing.ImageIcon;   
import java.awt.image.BufferedImage;
import java.io.File; 
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths; 
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;   
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 *
 * @author Ugur
 */

public class GUI extends JPanel {

    /**
     * Creates new form NewJPanel
     */
    public JFrame frame;
    public Rectangle solBarRectangle;
    public Rectangle sagBarRectangle;
    public Rectangle ortaBarRectangle;
    public TextAnalyzer textAnalizer;
    public ReverseImage imageAgent; 
    private String RapidAPI_KEY;
    private String ImgurCLIENT_ID; 
    private javax.swing.JLabel[] ResultComponents;
    
    //Constructor
    public GUI(JFrame frame, String RapidAPI_KEY, String ImgurCLIENT_ID) {
        initComponents();
        this.frame = frame;
        //Initialize my classes
        this.textAnalizer =  textAnalizer;
        this.imageAgent = imageAgent;
        
        //Set value of Bar to 0 
        Bar.setValue(0);
        //Set value of DepthSlider to 0
        DepthSlider.setValue(1); 
        
        //Initialize and activate my classes
        this.textAnalizer = new TextAnalyzer(RapidAPI_KEY);
        this.imageAgent = new ReverseImage(RapidAPI_KEY,ImgurCLIENT_ID);
        
        //Initialize Components showing scan results 
        ResultComponents = new javax.swing.JLabel[5];
        ResultComponents[0] = Result1;
        ResultComponents[1] = Result2;
        ResultComponents[2] = Result3;
        ResultComponents[3] = Result4;
        ResultComponents[4] = Result5;
        
        
    }  
    
    //Gets Entities of URL by scanning web and applying NLP named entity recognition to pages' content
    private ArrayList<String> getEntities(String URL){ 
        //Reset Message
        Message.setText("");
         
        //Scan webpage and get urls related to image
        ArrayList<String> list = imageAgent.find(URLField.getText(),DepthSlider.getValue(),Language.getSelectedItem().toString().split(" ")[1]);   
        //Remove Duplicates, google cache and unnecessary links for optimizing search 
        list = removeDuplicates(list); 
        list.removeIf(s -> s.contains("https://webcache.googleusercontent.com/search?q"));
        list.remove("#");
        
        //Check if list is null
        if(list==null){
            Message.setText("Can't Find Any Source");
            return null;
        } 
        
        //Store entity type
        String EntityType = SearchType2.getSelectedItem().toString();
        
        //Get entities using my textAnalizer Class 
        ArrayList<String> entities = textAnalizer.getEntities(list,Bar,EntityType, SearchType.getSelectedItem().toString().equals("PARALLEL"));  
        
        //If no entities relate to image found, set message
        if(entities.size()==0) 
            Message.setText("Can't find any "+EntityType+" type entity.");
          
        return entities;        
    }
    //Initialize GUI Components 
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        kGradientPanel1 = new keeptoo.KGradientPanel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        kButton1 = new keeptoo.KButton();
        kButton2 = new keeptoo.KButton();
        kButton3 = new keeptoo.KButton();
        kGradientPanel2 = new keeptoo.KGradientPanel();
        Closebtn = new javax.swing.JLabel();
        MaximizeBtn = new javax.swing.JLabel();
        MinimizeBtn = new javax.swing.JLabel();
        Content2 = new javax.swing.JPanel();
        kGradientPanel5 = new keeptoo.KGradientPanel();
        jPanel1 = new javax.swing.JPanel();
        kGradientPanel3 = new keeptoo.KGradientPanel();
        K = new javax.swing.JLabel();
        K1 = new javax.swing.JLabel();
        URLField = new javax.swing.JTextField();
        SearchButton = new keeptoo.KButton();
        Table = new keeptoo.KGradientPanel();
        ResultPanel1 = new keeptoo.KGradientPanel();
        Result1 = new javax.swing.JLabel();
        ResultPanel2 = new keeptoo.KGradientPanel();
        Result2 = new javax.swing.JLabel();
        ResultPanel3 = new keeptoo.KGradientPanel();
        Result3 = new javax.swing.JLabel();
        ResultPanel4 = new keeptoo.KGradientPanel();
        Result4 = new javax.swing.JLabel();
        ResultPanel5 = new keeptoo.KGradientPanel();
        Result5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        ImageDisplayPanel = new keeptoo.KGradientPanel();
        ImageDisplay = new javax.swing.JLabel();
        Bar = new javax.swing.JProgressBar();
        DepthSlider = new javax.swing.JSlider();
        SearchType = new javax.swing.JComboBox<>();
        SearchType2 = new javax.swing.JComboBox<>();
        Message = new javax.swing.JLabel();
        Language = new javax.swing.JComboBox<>();
        SearchButton1 = new keeptoo.KButton();
        Content1 = new javax.swing.JPanel();
        kGradientPanel7 = new keeptoo.KGradientPanel();
        kGradientPanel4 = new keeptoo.KGradientPanel();
        kGradientPanel10 = new keeptoo.KGradientPanel();
        kGradientPanel6 = new keeptoo.KGradientPanel();
        Content3 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(2, 14, 28));
        setForeground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1280, 720));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        kGradientPanel1.setkBorderRadius(0);
        kGradientPanel1.setkEndColor(new java.awt.Color(47, 38, 88));
        kGradientPanel1.setkStartColor(new java.awt.Color(47, 38, 88));
        kGradientPanel1.setkTransparentControls(false);
        kGradientPanel1.setLayout(new java.awt.GridLayout(5, 2, 10, 4));

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 122, Short.MAX_VALUE)
        );

        kGradientPanel1.add(jLayeredPane1);

        kButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(47, 38, 88)));
        kButton1.setkBorderRadius(0);
        kButton1.setkEndColor(new java.awt.Color(0, 103, 103));
        kButton1.setkHoverEndColor(new java.awt.Color(38, 31, 70));
        kButton1.setkHoverForeGround(new java.awt.Color(38, 31, 70));
        kButton1.setkHoverStartColor(new java.awt.Color(38, 31, 70));
        kButton1.setkStartColor(new java.awt.Color(17, 78, 128));
        kButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                content1(evt);
            }
        });
        kButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButton1ActionPerformed(evt);
            }
        });
        kGradientPanel1.add(kButton1);

        kButton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(47, 38, 88)));
        kButton2.setkBorderRadius(0);
        kButton2.setkEndColor(new java.awt.Color(0, 103, 103));
        kButton2.setkHoverEndColor(new java.awt.Color(38, 31, 70));
        kButton2.setkHoverForeGround(new java.awt.Color(38, 31, 70));
        kButton2.setkHoverStartColor(new java.awt.Color(38, 31, 70));
        kButton2.setkStartColor(new java.awt.Color(17, 78, 128));
        kButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                content2(evt);
            }
        });
        kButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButton2ActionPerformed(evt);
            }
        });
        kGradientPanel1.add(kButton2);

        kButton3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(47, 38, 88)));
        kButton3.setkBorderRadius(0);
        kButton3.setkEndColor(new java.awt.Color(0, 103, 103));
        kButton3.setkHoverEndColor(new java.awt.Color(38, 31, 70));
        kButton3.setkHoverForeGround(new java.awt.Color(38, 31, 70));
        kButton3.setkHoverStartColor(new java.awt.Color(38, 31, 70));
        kButton3.setkStartColor(new java.awt.Color(17, 78, 128));
        kButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                content3(evt);
            }
        });
        kButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButton3ActionPerformed(evt);
            }
        });
        kGradientPanel1.add(kButton3);

        add(kGradientPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 60, 630));

        kGradientPanel2.setBackground(new java.awt.Color(210, 210, 240));
        kGradientPanel2.setkBorderRadius(0);
        kGradientPanel2.setkEndColor(new java.awt.Color(0, 153, 153));
        kGradientPanel2.setkGradientFocus(800);
        kGradientPanel2.setkStartColor(new java.awt.Color(47, 38, 88));
        kGradientPanel2.setkTransparentControls(false);

        Closebtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-close-window-50.png"))); // NOI18N
        Closebtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Exit(evt);
            }
        });

        MaximizeBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-maximize-window-50.png"))); // NOI18N
        MaximizeBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Maximize(evt);
            }
        });

        MinimizeBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-minimize-window-50.png"))); // NOI18N
        MinimizeBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Minimize(evt);
            }
        });

        javax.swing.GroupLayout kGradientPanel2Layout = new javax.swing.GroupLayout(kGradientPanel2);
        kGradientPanel2.setLayout(kGradientPanel2Layout);
        kGradientPanel2Layout.setHorizontalGroup(
            kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel2Layout.createSequentialGroup()
                .addContainerGap(1268, Short.MAX_VALUE)
                .addComponent(MinimizeBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(MaximizeBtn)
                .addGap(10, 10, 10)
                .addComponent(Closebtn)
                .addGap(2, 2, 2))
        );
        kGradientPanel2Layout.setVerticalGroup(
            kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel2Layout.createSequentialGroup()
                .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Closebtn)
                    .addComponent(MaximizeBtn)
                    .addComponent(MinimizeBtn))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        add(kGradientPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 1290, 50));

        Content2.setBackground(new java.awt.Color(38, 31, 70));

        kGradientPanel5.setBackground(new java.awt.Color(2, 14, 28));
        kGradientPanel5.setkEndColor(new java.awt.Color(0, 153, 153));
        kGradientPanel5.setkStartColor(new java.awt.Color(47, 38, 88));

        jPanel1.setBackground(new java.awt.Color(2, 14, 28));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        kGradientPanel3.setBackground(new java.awt.Color(210, 210, 240));
        kGradientPanel3.setkBorderRadius(0);
        kGradientPanel3.setkEndColor(new java.awt.Color(0, 153, 153));
        kGradientPanel3.setkGradientFocus(800);
        kGradientPanel3.setkStartColor(new java.awt.Color(47, 38, 88));
        kGradientPanel3.setkTransparentControls(false);
        kGradientPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        K.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Image.png"))); // NOI18N
        K.setText("              ");
        kGradientPanel3.add(K, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, 50, 40));

        K1.setForeground(new java.awt.Color(255, 255, 255));
        K1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-search-40.png"))); // NOI18N
        K1.setText(" ");
        kGradientPanel3.add(K1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 0, 50, -1));

        jPanel1.add(kGradientPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 910, 40));

        URLField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Image URL", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("OCR A Extended", 0, 14))); // NOI18N
        URLField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                URLFieldActionPerformed(evt);
            }
        });
        jPanel1.add(URLField, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 420, 420, 50));

        SearchButton.setBorder(null);
        SearchButton.setText("Search");
        SearchButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        SearchButton.setkBackGroundColor(new java.awt.Color(255, 255, 255));
        SearchButton.setkBorderRadius(2);
        SearchButton.setkEndColor(new java.awt.Color(0, 0, 0));
        SearchButton.setkHoverColor(new java.awt.Color(0, 153, 153));
        SearchButton.setkHoverEndColor(new java.awt.Color(0, 153, 153));
        SearchButton.setkHoverForeGround(new java.awt.Color(0, 0, 0));
        SearchButton.setkHoverStartColor(new java.awt.Color(0, 153, 153));
        SearchButton.setkPressedColor(new java.awt.Color(0, 153, 153));
        SearchButton.setkSelectedColor(new java.awt.Color(255, 255, 255));
        SearchButton.setMultiClickThreshhold(4L);
        SearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchButtonActionPerformed(evt);
            }
        });
        SearchButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SearchButtonKeyPressed(evt);
            }
        });
        jPanel1.add(SearchButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 510, 150, 30));

        Table.setBackground(new java.awt.Color(38, 31, 70));
        Table.setkBorderRadius(0);
        Table.setkEndColor(new java.awt.Color(0, 153, 153));
        Table.setkGradientFocus(120);
        Table.setkStartColor(new java.awt.Color(38, 31, 70));
        Table.setkTransparentControls(false);
        Table.setPreferredSize(new java.awt.Dimension(500, 500));
        Table.setLayout(new java.awt.GridLayout(5, 5, 0, 4));

        ResultPanel1.setBackground(new java.awt.Color(38, 31, 70));
        ResultPanel1.setkBorderRadius(0);
        ResultPanel1.setkEndColor(new java.awt.Color(0, 153, 153));
        ResultPanel1.setkGradientFocus(120);
        ResultPanel1.setkStartColor(new java.awt.Color(38, 31, 70));
        ResultPanel1.setkTransparentControls(false);
        ResultPanel1.setPreferredSize(new java.awt.Dimension(500, 500));
        ResultPanel1.setLayout(null);

        Result1.setFont(new java.awt.Font("OCR A Extended", 1, 18)); // NOI18N
        Result1.setForeground(new java.awt.Color(255, 255, 255));
        Result1.setText(" ");
        ResultPanel1.add(Result1);
        Result1.setBounds(50, 20, 150, 50);

        Table.add(ResultPanel1);

        ResultPanel2.setBackground(new java.awt.Color(38, 31, 70));
        ResultPanel2.setkBorderRadius(0);
        ResultPanel2.setkEndColor(new java.awt.Color(0, 153, 153));
        ResultPanel2.setkGradientFocus(120);
        ResultPanel2.setkStartColor(new java.awt.Color(38, 31, 70));
        ResultPanel2.setkTransparentControls(false);
        ResultPanel2.setPreferredSize(new java.awt.Dimension(500, 500));
        ResultPanel2.setLayout(null);

        Result2.setFont(new java.awt.Font("OCR A Extended", 1, 18)); // NOI18N
        Result2.setForeground(new java.awt.Color(255, 255, 255));
        Result2.setText(" ");
        ResultPanel2.add(Result2);
        Result2.setBounds(50, 20, 150, 50);

        Table.add(ResultPanel2);

        ResultPanel3.setBackground(new java.awt.Color(38, 31, 70));
        ResultPanel3.setkBorderRadius(0);
        ResultPanel3.setkEndColor(new java.awt.Color(0, 153, 153));
        ResultPanel3.setkGradientFocus(120);
        ResultPanel3.setkStartColor(new java.awt.Color(38, 31, 70));
        ResultPanel3.setkTransparentControls(false);
        ResultPanel3.setPreferredSize(new java.awt.Dimension(500, 500));
        ResultPanel3.setLayout(null);

        Result3.setFont(new java.awt.Font("OCR A Extended", 1, 18)); // NOI18N
        Result3.setForeground(new java.awt.Color(255, 255, 255));
        Result3.setText(" ");
        ResultPanel3.add(Result3);
        Result3.setBounds(50, 20, 150, 50);

        Table.add(ResultPanel3);

        ResultPanel4.setBackground(new java.awt.Color(38, 31, 70));
        ResultPanel4.setkBorderRadius(0);
        ResultPanel4.setkEndColor(new java.awt.Color(0, 153, 153));
        ResultPanel4.setkGradientFocus(120);
        ResultPanel4.setkStartColor(new java.awt.Color(38, 31, 70));
        ResultPanel4.setkTransparentControls(false);
        ResultPanel4.setPreferredSize(new java.awt.Dimension(500, 500));
        ResultPanel4.setLayout(null);

        Result4.setFont(new java.awt.Font("OCR A Extended", 1, 18)); // NOI18N
        Result4.setForeground(new java.awt.Color(255, 255, 255));
        Result4.setText(" ");
        ResultPanel4.add(Result4);
        Result4.setBounds(50, 20, 150, 50);

        Table.add(ResultPanel4);

        ResultPanel5.setBackground(new java.awt.Color(38, 31, 70));
        ResultPanel5.setkBorderRadius(0);
        ResultPanel5.setkEndColor(new java.awt.Color(0, 153, 153));
        ResultPanel5.setkGradientFocus(120);
        ResultPanel5.setkStartColor(new java.awt.Color(38, 31, 70));
        ResultPanel5.setkTransparentControls(false);
        ResultPanel5.setPreferredSize(new java.awt.Dimension(500, 500));
        ResultPanel5.setLayout(null);

        Result5.setFont(new java.awt.Font("OCR A Extended", 1, 18)); // NOI18N
        Result5.setForeground(new java.awt.Color(255, 255, 255));
        Result5.setText(" ");
        ResultPanel5.add(Result5);
        Result5.setBounds(50, 20, 150, 50);

        Table.add(ResultPanel5);

        jPanel1.add(Table, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 220, 510));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Image", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("OCR A Extended", 0, 14))); // NOI18N
        jPanel2.setLayout(null);

        ImageDisplayPanel.setBackground(new java.awt.Color(38, 31, 70));
        ImageDisplayPanel.setkBorderRadius(0);
        ImageDisplayPanel.setkEndColor(new java.awt.Color(0, 153, 153));
        ImageDisplayPanel.setkGradientFocus(120);
        ImageDisplayPanel.setkStartColor(new java.awt.Color(38, 31, 70));
        ImageDisplayPanel.setkTransparentControls(false);
        ImageDisplayPanel.setPreferredSize(new java.awt.Dimension(500, 500));
        ImageDisplayPanel.setLayout(null);

        ImageDisplay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ImageDisplay.setText("     ");
        ImageDisplay.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ImageDisplayPanel.add(ImageDisplay);
        ImageDisplay.setBounds(0, 0, 15, 14);

        jPanel2.add(ImageDisplayPanel);
        ImageDisplayPanel.setBounds(10, 30, 510, 268);

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 70, 530, 310));

        Bar.setBackground(new java.awt.Color(255, 255, 255));
        Bar.setForeground(new java.awt.Color(0, 153, 153));
        jPanel1.add(Bar, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 390, 420, 20));

        DepthSlider.setMajorTickSpacing(4);
        DepthSlider.setMaximum(20);
        DepthSlider.setMinimum(1);
        DepthSlider.setMinorTickSpacing(2);
        DepthSlider.setPaintLabels(true);
        DepthSlider.setPaintTicks(true);
        DepthSlider.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search Depth", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("OCR A Extended", 0, 14))); // NOI18N
        jPanel1.add(DepthSlider, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 480, 160, 60));

        SearchType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NORMAL (Recommended)", "PARALLEL" }));
        SearchType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchTypeActionPerformed(evt);
            }
        });
        jPanel1.add(SearchType, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 480, 150, 20));

        SearchType2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PERSON", "ORG", "LOC", "DATE", "PRODUCT", "LAW" }));
        SearchType2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchType2ActionPerformed(evt);
            }
        });
        jPanel1.add(SearchType2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 480, 90, 25));

        Message.setForeground(new java.awt.Color(0, 153, 153));
        Message.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Message.setText("  ");
        jPanel1.add(Message, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 40, 530, 30));

        Language.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "English en", "current current ", "Turkish tr", "German de", "Turkmen tk", "Russian ru", "Korean ko", "Spanish es" }));
        Language.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LanguageActionPerformed(evt);
            }
        });
        jPanel1.add(Language, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 510, 90, 25));

        SearchButton1.setBorder(null);
        SearchButton1.setText("Local");
        SearchButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        SearchButton1.setkBackGroundColor(new java.awt.Color(255, 255, 255));
        SearchButton1.setkBorderRadius(2);
        SearchButton1.setkEndColor(new java.awt.Color(0, 0, 0));
        SearchButton1.setkHoverColor(new java.awt.Color(0, 153, 153));
        SearchButton1.setkHoverEndColor(new java.awt.Color(0, 153, 153));
        SearchButton1.setkHoverForeGround(new java.awt.Color(0, 0, 0));
        SearchButton1.setkHoverStartColor(new java.awt.Color(0, 153, 153));
        SearchButton1.setkPressedColor(new java.awt.Color(0, 153, 153));
        SearchButton1.setkSelectedColor(new java.awt.Color(255, 255, 255));
        SearchButton1.setMultiClickThreshhold(4L);
        SearchButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchButton1ActionPerformed(evt);
            }
        });
        SearchButton1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SearchButton1KeyPressed(evt);
            }
        });
        jPanel1.add(SearchButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 420, 50, 50));

        javax.swing.GroupLayout kGradientPanel5Layout = new javax.swing.GroupLayout(kGradientPanel5);
        kGradientPanel5.setLayout(kGradientPanel5Layout);
        kGradientPanel5Layout.setHorizontalGroup(
            kGradientPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        kGradientPanel5Layout.setVerticalGroup(
            kGradientPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel5Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 549, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout Content2Layout = new javax.swing.GroupLayout(Content2);
        Content2.setLayout(Content2Layout);
        Content2Layout.setHorizontalGroup(
            Content2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 930, Short.MAX_VALUE)
        );
        Content2Layout.setVerticalGroup(
            Content2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Content2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(kGradientPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        add(Content2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, 930, 560));

        Content1.setBackground(new java.awt.Color(2, 14, 28));
        Content1.setLayout(new java.awt.GridLayout(2, 2, 80, 80));

        kGradientPanel7.setBackground(new java.awt.Color(38, 31, 70));
        kGradientPanel7.setkBorderRadius(0);
        kGradientPanel7.setkEndColor(new java.awt.Color(0, 153, 153));
        kGradientPanel7.setkGradientFocus(120);
        kGradientPanel7.setkStartColor(new java.awt.Color(38, 31, 70));
        kGradientPanel7.setkTransparentControls(false);
        kGradientPanel7.setPreferredSize(new java.awt.Dimension(500, 500));

        javax.swing.GroupLayout kGradientPanel7Layout = new javax.swing.GroupLayout(kGradientPanel7);
        kGradientPanel7.setLayout(kGradientPanel7Layout);
        kGradientPanel7Layout.setHorizontalGroup(
            kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 425, Short.MAX_VALUE)
        );
        kGradientPanel7Layout.setVerticalGroup(
            kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 240, Short.MAX_VALUE)
        );

        Content1.add(kGradientPanel7);

        kGradientPanel4.setBackground(new java.awt.Color(38, 31, 70));
        kGradientPanel4.setkBorderRadius(0);
        kGradientPanel4.setkEndColor(new java.awt.Color(0, 153, 153));
        kGradientPanel4.setkGradientFocus(120);
        kGradientPanel4.setkStartColor(new java.awt.Color(38, 31, 70));
        kGradientPanel4.setkTransparentControls(false);
        kGradientPanel4.setPreferredSize(new java.awt.Dimension(500, 500));

        javax.swing.GroupLayout kGradientPanel4Layout = new javax.swing.GroupLayout(kGradientPanel4);
        kGradientPanel4.setLayout(kGradientPanel4Layout);
        kGradientPanel4Layout.setHorizontalGroup(
            kGradientPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 425, Short.MAX_VALUE)
        );
        kGradientPanel4Layout.setVerticalGroup(
            kGradientPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 240, Short.MAX_VALUE)
        );

        Content1.add(kGradientPanel4);

        kGradientPanel10.setBackground(new java.awt.Color(38, 31, 70));
        kGradientPanel10.setkBorderRadius(0);
        kGradientPanel10.setkEndColor(new java.awt.Color(0, 153, 153));
        kGradientPanel10.setkGradientFocus(120);
        kGradientPanel10.setkStartColor(new java.awt.Color(38, 31, 70));
        kGradientPanel10.setkTransparentControls(false);
        kGradientPanel10.setPreferredSize(new java.awt.Dimension(500, 500));

        javax.swing.GroupLayout kGradientPanel10Layout = new javax.swing.GroupLayout(kGradientPanel10);
        kGradientPanel10.setLayout(kGradientPanel10Layout);
        kGradientPanel10Layout.setHorizontalGroup(
            kGradientPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 425, Short.MAX_VALUE)
        );
        kGradientPanel10Layout.setVerticalGroup(
            kGradientPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 240, Short.MAX_VALUE)
        );

        Content1.add(kGradientPanel10);

        kGradientPanel6.setBackground(new java.awt.Color(38, 31, 70));
        kGradientPanel6.setkBorderRadius(0);
        kGradientPanel6.setkEndColor(new java.awt.Color(0, 153, 153));
        kGradientPanel6.setkGradientFocus(120);
        kGradientPanel6.setkStartColor(new java.awt.Color(38, 31, 70));
        kGradientPanel6.setkTransparentControls(false);
        kGradientPanel6.setPreferredSize(new java.awt.Dimension(500, 500));

        javax.swing.GroupLayout kGradientPanel6Layout = new javax.swing.GroupLayout(kGradientPanel6);
        kGradientPanel6.setLayout(kGradientPanel6Layout);
        kGradientPanel6Layout.setHorizontalGroup(
            kGradientPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 425, Short.MAX_VALUE)
        );
        kGradientPanel6Layout.setVerticalGroup(
            kGradientPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 240, Short.MAX_VALUE)
        );

        Content1.add(kGradientPanel6);

        add(Content1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, 930, 560));

        Content3.setBackground(new java.awt.Color(38, 31, 70));

        javax.swing.GroupLayout Content3Layout = new javax.swing.GroupLayout(Content3);
        Content3.setLayout(Content3Layout);
        Content3Layout.setHorizontalGroup(
            Content3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 930, Short.MAX_VALUE)
        );
        Content3Layout.setVerticalGroup(
            Content3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 560, Short.MAX_VALUE)
        );

        add(Content3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, 930, 560));
    }// </editor-fold>//GEN-END:initComponents

    
    private void kButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kButton1ActionPerformed

    private void kButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kButton2ActionPerformed

    private void Exit(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Exit
        // TODO add your handling code here:
           
        frame.dispose();
        
    }//GEN-LAST:event_Exit
    private void Maximize(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Maximize
        // TODO add your handling code here:
        if(frame.getExtendedState()==JFrame.NORMAL)
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        else
            frame.setExtendedState(JFrame.NORMAL);
      
    }//GEN-LAST:event_Maximize

    private void Minimize(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Minimize
        // TODO add your handling code here:
        frame.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_Minimize
    
    //Set image to given jlabel component with wanted image, width and height
    private void setImage(javax.swing.JLabel label,String ImagePath,int w, int h){
        ImageIcon myImage = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource(ImagePath))); 
        Image img1 = myImage.getImage();
        Image img2 = img1.getScaledInstance(w,h,Image.SCALE_SMOOTH);
        ImageIcon i = new ImageIcon(img2);
        label.setIcon(i);
    }  
    //First Tab
    private void content1(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_content1
        // TODO add your handling code here:
        Content3.setVisible(false);
        Content2.setVisible(false);
        Content1.setVisible(true);
        kButton3.setkFillButton(true);
        kButton2.setkFillButton(true);
        kButton1.setkFillButton(false);
        
    }//GEN-LAST:event_content1

    //Second Tab
    private void content2(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_content2
        // TODO add your handling code here:
        Content3.setVisible(false);
        Content2.setVisible(true);
        Content1.setVisible(false); 
        
        kButton3.setkFillButton(true);
        kButton2.setkFillButton(false);
        kButton1.setkFillButton(true);  
    }//GEN-LAST:event_content2
    
    //Third Tab
    private void content3(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_content3
        // TODO add your handling code here:
        Content3.setVisible(true);
        Content2.setVisible(false);
        Content1.setVisible(false);
        kButton3.setkFillButton(false);
        kButton2.setkFillButton(true);
        kButton1.setkFillButton(true);

    }//GEN-LAST:event_content3

    private void kButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kButton3ActionPerformed

    private void URLFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_URLFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_URLFieldActionPerformed
    
    //Nested Class for displaying scanned image 
    class DisplayImage implements Runnable{
        private String URL; 
        public DisplayImage(String URL){
            this.URL = URL;
        }
        public void run(){
            
            try{
                
                BufferedImage image = null; 
                
                //Check if image is from web or local storage 
                if(new File(URL).isDirectory() && Files.exists(Paths.get(URL))){
                    image = ImageIO.read(new File(URL));
                }
                else{
                    image = ImageIO.read(new URL(URL));
                }
                
                //Get image's dimensions nd ratio
                int w = image.getWidth();
                int h = image.getHeight();
                float ratio = (float)w/(float)h;
                
                //Get Display Panel's dimensions
                int pw =ImageDisplayPanel.getWidth();
                int ph = ImageDisplayPanel.getHeight(); 
                
                //Resizing the image without breaking its ratio
                if(w>h){
                    w = pw;
                    h = (int)(w/ratio);
                }
                else{
                    h = ph;
                    w = (int)(ratio*h);
                }
                //Resize and Image and set image as icon to display     
                Image dimg = image.getScaledInstance(w , h  ,
            Image.SCALE_SMOOTH);
                ImageDisplay.setIcon(new ImageIcon(dimg)); 
                ImageDisplay.setBounds(pw/2-w/2, 0, w, h);  
            }
            catch(Exception e){ 
                e.printStackTrace();
                System.out.println("Image Cannot Be Displayed");
                Message.setText("Image Cannot Be Displayed at Panel");
            } 
        }
        
    }
    
    //Commands when search button pressed
    private void SearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchButtonActionPerformed
        // Reset Bar's value  
        Bar.setValue(0);
        
        //Get URL/Path from Textfield component
        String URL = URLField.getText(); 
        //Display URL in parallel to main commands with Threading
        new DisplayImage(URL).run();
        
        //Get Entities of given image by Scanning web and applying NLP Named Entity Recognition to found pages 
        ArrayList<String> entities = getEntities(URL ); 
        
        //Check NULL
        if(entities!=null){
            //Display most relevant entities (max 5 entities)   
            int displayResultCount = Math.min(5,entities.size());
            for(int i=0;i<displayResultCount;i++)
                this.ResultComponents[i].setText(entities.get(i)); 
        } 
    }//GEN-LAST:event_SearchButtonActionPerformed

    private void SearchButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchButtonKeyPressed
        
    }//GEN-LAST:event_SearchButtonKeyPressed

    private void SearchTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SearchTypeActionPerformed

    private void SearchType2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchType2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SearchType2ActionPerformed

    private void LanguageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LanguageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LanguageActionPerformed
    //Choose File from local storage
    private void SearchButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchButton1ActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files", "png", "jpg","jpeg"));
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile(); 
            URLField.setText(selectedFile.getAbsolutePath());
        }
        
    }//GEN-LAST:event_SearchButton1ActionPerformed

    private void SearchButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchButton1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SearchButton1KeyPressed
    //Static Template function for removeing duplicates from lists with all kinds of type
    public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list) 
    { 
  
        // Create a new ArrayList 
        ArrayList<T> newList = new ArrayList<T>(); 
  
        // Traverse through the first list 
        for (T element : list) { 
  
            // If this element is not present in newList 
            // then add it 
            if (!newList.contains(element)) { 
  
                newList.add(element); 
            } 
        } 
  
        // return the new list 
        return newList; 
    } 
    
    //MAIN
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
               
                //Initialize Tests
                new Tests(); 
                JFrame frame = new JFrame("ImageOPS Tool");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setUndecorated(true);
                frame.setResizable(true);
                 
                //Set necessary api keys
                String Key1 = "YOUR_RAPIDAPI_KEY";
                String Key2 = "YOUR_IMGUR_CLIENT_ID";
                
                //Initialize Graphical User Interface for frame
                GUI GUI = new GUI( frame,Key1,Key2);
                frame.add(GUI); 
                frame.pack();
                frame.setLocationRelativeTo(null);
                UIManager.put("JFrame.activeTitleBackground", Color.red);
                
                //Set frame visible
                frame.setVisible(true); 
                
                
                //Add listener to Progress Bar to track current progress of scan
                Bar.addChangeListener(  new javax.swing.event.ChangeListener() {
                    public void stateChanged(javax.swing.event.ChangeEvent evt) {
                      javax.swing.JProgressBar  cc = (javax.swing.JProgressBar) evt.getSource();
                      int value = cc.getValue();
                      int min = cc.getMinimum();
                      int max = cc.getMaximum(); 
                      
                      //Paint when value changes
                      Bar.paintImmediately(Bar.getVisibleRect()); 
                    } 
                });

            }
        });
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JProgressBar Bar;
    private javax.swing.JLabel Closebtn;
    public static javax.swing.JPanel Content1;
    private static javax.swing.JPanel Content2;
    private static javax.swing.JPanel Content3;
    private javax.swing.JSlider DepthSlider;
    private javax.swing.JLabel ImageDisplay;
    private keeptoo.KGradientPanel ImageDisplayPanel;
    private javax.swing.JLabel K;
    private javax.swing.JLabel K1;
    private javax.swing.JComboBox<String> Language;
    private javax.swing.JLabel MaximizeBtn;
    private javax.swing.JLabel Message;
    private javax.swing.JLabel MinimizeBtn;
    private javax.swing.JLabel Result1;
    private javax.swing.JLabel Result2;
    private javax.swing.JLabel Result3;
    private javax.swing.JLabel Result4;
    private javax.swing.JLabel Result5;
    private keeptoo.KGradientPanel ResultPanel1;
    private keeptoo.KGradientPanel ResultPanel2;
    private keeptoo.KGradientPanel ResultPanel3;
    private keeptoo.KGradientPanel ResultPanel4;
    private keeptoo.KGradientPanel ResultPanel5;
    private keeptoo.KButton SearchButton;
    private keeptoo.KButton SearchButton1;
    private javax.swing.JComboBox<String> SearchType;
    private javax.swing.JComboBox<String> SearchType2;
    private keeptoo.KGradientPanel Table;
    private javax.swing.JTextField URLField;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private keeptoo.KButton kButton1;
    private keeptoo.KButton kButton2;
    private keeptoo.KButton kButton3;
    private keeptoo.KGradientPanel kGradientPanel1;
    private keeptoo.KGradientPanel kGradientPanel10;
    private keeptoo.KGradientPanel kGradientPanel2;
    private keeptoo.KGradientPanel kGradientPanel3;
    private keeptoo.KGradientPanel kGradientPanel4;
    private keeptoo.KGradientPanel kGradientPanel5;
    private keeptoo.KGradientPanel kGradientPanel6;
    private keeptoo.KGradientPanel kGradientPanel7;
    // End of variables declaration//GEN-END:variables
}
