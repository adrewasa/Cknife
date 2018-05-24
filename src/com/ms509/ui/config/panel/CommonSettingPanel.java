package com.ms509.ui.config.panel;

import com.ms509.constant.CommonConstant;
import com.ms509.ui.ConfigDialog;
import com.ms509.util.Configuration;
import com.ms509.util.GBC;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by andrew_asa on 2018/5/23.
 * 通用设置
 */
public class CommonSettingPanel extends JPanel {

    private JTextField jTdownloadBasePath;

    private CommonSettingPanel self;

    public CommonSettingPanel() {

        self = this;
        this.setLayout(new GridBagLayout());
        GBC gbclDownloadBasePath = new GBC(0, 0).setInsets(5, -40, 0, 0);
        GBC gbchDownloadBasePath = new GBC(1, 0, 3, 1).setInsets(5, 20, 0, 0);
        GBC gbchDownloadBasePathSelect = new GBC(5, 0, 5, 1).setInsets(5, 7, 0, 0);
        GBC gbcok = new GBC(2, 1, 1, 1).setInsets(10, 5, 0, 0);
        GBC gbccancle = new GBC(3, 1, 1, 1).setInsets(10, 5, 0, 0);
        Dimension dim = new Dimension(200, 23);
        JLabel jLDownloadBasePath = new JLabel("存放文件夹:");
        JButton jbDownloadBasePathSelect = new JButton("选择");
        jTdownloadBasePath = new JTextField();
        jTdownloadBasePath.setPreferredSize(dim);
        //lDownloadBasePathSelect.setPreferredSize(new Dimension(23,23));
        JButton ok = new JButton("确定");
        JButton cancle = new JButton("取消");
        this.add(jLDownloadBasePath, gbclDownloadBasePath);
        this.add(jTdownloadBasePath, gbchDownloadBasePath);
        this.add(jbDownloadBasePathSelect, gbchDownloadBasePathSelect);
        this.add(ok, gbcok);
        this.add(cancle, gbccancle);
        cancle.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                ConfigDialog.cdialog.hide();
            }
        });
        ok.addActionListener(new CommonSettingPanel.ButtonAction());
        jbDownloadBasePathSelect.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {


                JFileChooser select = new JFileChooser(".");
                select.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                select.setApproveButtonText("选择");
                select.setDialogTitle("选择下载保存目录");
                int mod = select.showSaveDialog(null);
                if (mod == JFileChooser.APPROVE_OPTION) {
                    File file = select.getSelectedFile();
                    jTdownloadBasePath.setText(file.getAbsolutePath());
                }
            }
        });
        Configuration config = new Configuration();
        jTdownloadBasePath.setText(config.getValue(CommonConstant.CONF.DOWNLOAD_BASE_DIR));
    }

    class ButtonAction implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            Configuration config = new Configuration();
            String text = jTdownloadBasePath.getText();
            if (text != null) {
                config.setValue(CommonConstant.CONF.DOWNLOAD_BASE_DIR, text);
            }
            ConfigDialog.cdialog.hide();
        }
    }
}
