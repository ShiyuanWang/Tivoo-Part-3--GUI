package Viewer;

import input.CalParser;
import input.CalendarUtil;
import input.Event;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import output.HtmlPageWriters;
import output.SummaryListPage;
import processor.DetailFilter;
import processor.ExcludeFilter;
import processor.KeywordFilter;
import processor.RangeOfDatesTimeFilter;
import processor.SortByEndDate;
import processor.SortByStartDate;
import processor.SortByTitle;
import processor.TimeFilter;




public class TivooView extends JFrame{
   
	private JComboBox typeChoice;
	CalParser calParser;
    List<Event> filterEvent;
    JEditorPane pane;
    JButton load;
	JButton parser;
	Container con;
	public static final String PRIFIX = "file:/I:/java_android/ShiyuanWang/workspace/ShiyuanWang_Tivoo/";
	public static final Dimension SIZE = new Dimension(800, 600);
	CalendarUtil util;
	private JTextField keyWordContainer;
	private JComboBox sortChoice;
	private JTextField timeFilterContainer;
	private JEditorPane myPage;
	private JLabel myStatus;
	private HtmlPageWriters htmlGenerator;
	private Browser display;
	private List<String> fileList;
	private JButton Display;
    public static final String BLANK = " "; 
    protected JTextArea myOutput;
    protected JTextField myMessage;
  
	
	
	public TivooView() throws IOException 
   {
           
	        // create container that will work with Window manager
		    fileList = new ArrayList<String>();
	        setTitle("Tivoo System");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        display = new Browser();
	        // add our user interface components to Frame and show it
	        getContentPane().add(display);  
	        pack();
	        makeMenu();
	        setVisible(true);
	        
   }

	private void makeMenu() {
		JMenuBar bar = new JMenuBar();
		bar.add(getInputMenu());
		bar.add(getOutMenu());
		 setJMenuBar(bar);
	}

	private JMenu getInputMenu() {
		JMenu Input = new JMenu("Input");
		Input.add(new AbstractAction("Load") {
            public void actionPerformed(ActionEvent ev) {
          
            	JFileChooser fc = new JFileChooser();	        
     	       int returnVal = fc.showOpenDialog(null); 
     	    	  if (returnVal == JFileChooser.APPROVE_OPTION) {
     	           File file = fc.getSelectedFile();
     	           String[] path = file.toURI().toString().split("/");
     	           String  fileName = "resources/"+path[path.length-1];
     	          fileList.add(fileName);
     	           }
            }
        });
        return Input;
	}
	
	private JMenu getOutMenu()
	{
		JMenu outPut = new JMenu("Output");
		outPut.add(new AbstractAction("Gen HTML")
		{
			public void actionPerformed(ActionEvent ev)
			{
				try {
					GenHTML();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			private void GenHTML() throws IOException {
				String[] keyword =  keyWordContainer.getText().split(" ");
				String timeinfo = timeFilterContainer.getText();
				String sort = (String) sortChoice.getSelectedItem();
				String type = (String) typeChoice.getSelectedItem();
				
				
				String[] files = (String[])fileList.toArray(new String[0]);
				util = new CalendarUtil();
				filterEvent = util.parseFiles(files);
	            process(keyword,timeinfo,sort,type);
			    htmlGenerator = new SummaryListPage();	
			    htmlGenerator.write(filterEvent);
			    display.showPage(PRIFIX+htmlGenerator.getFileName());
		//	    display.showPage("");
				
			}

			private void process(String[] keyword, String timeinfo,
					String sort, String type) {
			    timeinfo = timeinfo.trim();
				
				if(type.equals("ExcludeTitle"))
	            {
	            	filterEvent = new ExcludeFilter().filter(filterEvent,keyword); 
	            }
	            if(type.equals("Title"))
	            {
	            	filterEvent = new KeywordFilter().filter(filterEvent,keyword);
	            }
	            if(type.equals("Description"))
	            {
	            	filterEvent = new DetailFilter().filter(filterEvent,keyword);
	            }
			    if(timeinfo.contains("~"))
			    {
			    	String[] time = new String[1];
			    	time[0]=timeinfo;
			    	filterEvent = new RangeOfDatesTimeFilter().filter(filterEvent,time);

			    }
			    if(!timeinfo.contains("~")&&!timeinfo.equals(""))
			    {
			    	String[]time = new String[1];
			    	time[0] = timeinfo+"~"+timeinfo;
			    	filterEvent = new TimeFilter().filter(filterEvent,time);
			    }
			    if(sort.equals("ByStartTime"))
			    {   
			    	filterEvent =  new SortByStartDate().sort(filterEvent);
			    }
			    if(sort.equals("ByEndTime"))
			    {
			    	filterEvent = new SortByEndDate().sort(filterEvent);
			    }
			    if(sort.equals("ByTitle"))
			    {
			    	filterEvent = new SortByTitle().sort(filterEvent);
			    }
			}

		
		});
		return outPut;
	}


	public class Browser  extends JPanel implements ActionListener{

		public Browser()
		{
			setLayout(new BorderLayout());
	        add(makePageDisplay(), BorderLayout.CENTER);
	        add(makeProcessPanel(), BorderLayout.NORTH);
	        add(makeInformationPanel(), BorderLayout.SOUTH);
		}
		
		
		
		 private JComponent makeProcessPanel ()
		    {
			    JPanel panel = new JPanel(new BorderLayout());
		        panel.add(getFilterPanel(),BorderLayout.NORTH);
		        panel.add(getSortPanel(),BorderLayout.SOUTH);
		        return panel;
		    }
		    

		   private Component getSortPanel() {
			JPanel panel = new JPanel();
			JLabel sort = new JLabel("sort");
			panel.add(sort);
			String[] sortType = new String[] {"ByStartTime", "ByEndTime", "ByTitle"};
			sortChoice = new JComboBox(sortType);
			panel.add(sortChoice);
			return panel;
		}



		private Component getFilterPanel() {
			    JPanel panel = new JPanel();
			    panel.add(getKeyWordPanel(),BorderLayout.NORTH);
			    panel.add(getTimeFilterPanel(),BorderLayout.SOUTH);
			return panel;
		}
             
		   private JPanel getKeyWordPanel()
              {
            	  JPanel panel = new JPanel();
            	  
            	  JPanel keywordInput = new JPanel();
            	  JLabel lblName1 = new JLabel("KeyWord: ");
          		  keywordInput.add(lblName1);
            	  keyWordContainer = new JTextField(35);
                  keyWordContainer.addActionListener(this);
                  keywordInput.add(keyWordContainer);
                  
                  JPanel typePanel = new JPanel();
                  JLabel lblName2 = new JLabel("WordType: ");
          		  typePanel.add(lblName2);
                  String[] typeStrings = {"Title", "Desricption","ExcludeTitle"};
                  typeChoice = new JComboBox(typeStrings);
                  typePanel.add(typeChoice);
            	  panel.add(keywordInput,BorderLayout.WEST);
            	  panel.add(typeChoice,BorderLayout.EAST);
                  return panel;
            	  
              }

            private JPanel getTimeFilterPanel()
            {
            	JPanel panel = new JPanel();
            	JLabel time = new JLabel("Time");
            	panel.add(time);
            	timeFilterContainer = new JTextField(35); 
            	timeFilterContainer.addActionListener(this);
            	panel.add(timeFilterContainer);
            	return panel;            	
            	
            }
	   
	    private JComponent makeInformationPanel ()
	    {
	        JPanel panel = new JPanel();
	        Display = new JButton("Display");
	        Display.addActionListener(this);
	        panel.add(Display);
	    	myStatus = new JLabel(BLANK);
	        panel.add(myStatus);
	        return panel;
	    }
	    private JComponent makePageDisplay ()
		{
	        myPage = new JEditorPane();
	        myPage.setPreferredSize(SIZE);
            myPage.setEditable(false);
	        myPage.addHyperlinkListener(new LinkFollower());
			return new JScrollPane(myPage);
		}
		
		   private void update (String url) throws IOException
		   {
			   
		           myPage.setPage(url);
		       
		   }

		   private class LinkFollower implements HyperlinkListener
		   {
		       public void hyperlinkUpdate (HyperlinkEvent evt)
		       {
		           if (evt.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
		           {
		           	try {
						showPage(evt.getURL().toString());
					} catch (IOException e) {
						e.printStackTrace();
					}
		           }
		           else if (evt.getEventType() == HyperlinkEvent.EventType.ENTERED)
		           {
		           	showStatus(evt.getURL().toString());
		           }
		           else if (evt.getEventType() == HyperlinkEvent.EventType.EXITED)
		           {
		               showStatus(BLANK);
		           }
		       }
		   }
		   /**
		    * Display given message as information in the GUI.
		    */
		   public void showStatus (String message)
		   {
		       myStatus.setText(message);
		   }
		  
		  public void showPage (String url) throws IOException
		   {    
		 
		           new URL(url);
		           update(url);
		     
		   }



		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==Display)
			{
				try {
					display.showPage(PRIFIX+"/output/summary_List.html");
				} catch (IOException e1) {

					e1.printStackTrace();
				}
			}
			
		}
	}
	
	public static void main(String[] args) throws IOException {
	
		    	 TivooView foo = new TivooView();
		            

		    }

}
