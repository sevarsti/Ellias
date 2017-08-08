/*      */ package com.ogzq.util;
/*      */ 
/*      */ import java.io.BufferedReader;
/*      */ import java.io.File;
/*      */ import java.io.FileInputStream;
/*      */ import java.io.FileOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.InputStreamReader;
/*      */ import java.io.OutputStream;
/*      */ import java.io.OutputStreamWriter;
/*      */ import java.io.PrintWriter;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collection;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedList;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.StringTokenizer;
/*      */ 
/*      */ public class IniEditor
/*      */ {
/*  133 */   private static boolean DEFAULT_CASE_SENSITIVITY = false;
/*      */   private Map<String, Section> sections;
/*      */   private List<String> sectionOrder;
/*      */   private String commonName;
/*      */   private char[] commentDelims;
/*      */   private boolean isCaseSensitive;
/*      */   private OptionFormat optionFormat;
/* 1005 */   private static final Line BLANK_LINE = new Line() {
/*      */     public String toString() {
/* 1007 */       return "";
/*      */     }
/* 1005 */   };
/*      */ 
/*      */   public IniEditor()
/*      */   {
/*  146 */     this(null, null);
/*      */   }
/*      */ 
/*      */   public IniEditor(boolean isCaseSensitive)
/*      */   {
/*  156 */     this(null, null, isCaseSensitive);
/*      */   }
/*      */ 
/*      */   public IniEditor(String commonName)
/*      */   {
/*  166 */     this(commonName, null);
/*      */   }
/*      */ 
/*      */   public IniEditor(String commonName, boolean isCaseSensitive)
/*      */   {
/*  178 */     this(commonName, null, isCaseSensitive);
/*      */   }
/*      */ 
/*      */   public IniEditor(char[] delims)
/*      */   {
/*  189 */     this(null, delims);
/*      */   }
/*      */ 
/*      */   public IniEditor(char[] delims, boolean isCaseSensitive)
/*      */   {
/*  202 */     this(null, delims, isCaseSensitive);
/*      */   }
/*      */ 
/*      */   public IniEditor(String commonName, char[] delims)
/*      */   {
/*  216 */     this(commonName, delims, DEFAULT_CASE_SENSITIVITY);
/*      */   }
/*      */ 
/*      */   public IniEditor(String commonName, char[] delims, boolean isCaseSensitive)
/*      */   {
/*  230 */     this.sections = new HashMap();
/*  231 */     this.sectionOrder = new LinkedList();
/*  232 */     this.isCaseSensitive = isCaseSensitive;
/*  233 */     if (commonName != null) {
/*  234 */       this.commonName = commonName;
/*  235 */       addSection(this.commonName);
/*      */     }
/*  237 */     this.commentDelims = delims;
/*  238 */     this.optionFormat = new OptionFormat("%s %s %s");
/*      */   }
/*      */ 
/*      */   public void setOptionFormatString(String formatString)
/*      */   {
/*  260 */     this.optionFormat = new OptionFormat(formatString);
/*      */   }
/*      */ 
/*      */   public String get(String section, String option)
/*      */   {
/*  275 */     if (hasSection(section)) {
/*  276 */       Section sect = getSection(section);
/*  277 */       if (sect.hasOption(option)) {
/*  278 */         return sect.get(option);
/*      */       }
/*  280 */       if (this.commonName != null) {
/*  281 */         return getSection(this.commonName).get(option);
/*      */       }
/*      */     }
/*  284 */     return null;
/*      */   }
/*      */ 
/*      */   public void set(String section, String option, String value)
/*      */   {
/*  301 */     if (hasSection(section))
/*  302 */       getSection(section).set(option, value);
/*      */     else
/*  304 */       throw new NoSuchSectionException(section);
/*      */   }
/*      */ 
/*      */   public boolean remove(String section, String option)
/*      */   {
/*  318 */     if (hasSection(section)) {
/*  319 */       return getSection(section).remove(option);
/*      */     }
/*  321 */     throw new NoSuchSectionException(section);
/*      */   }
/*      */ 
/*      */   public boolean hasOption(String section, String option)
/*      */   {
/*  335 */     return (hasSection(section)) && (getSection(section).hasOption(option));
/*      */   }
/*      */ 
/*      */   public boolean hasSection(String name)
/*      */   {
/*  345 */     return this.sections.containsKey(normSection(name));
/*      */   }
/*      */ 
/*      */   public boolean addSection(String name)
/*      */   {
/*  357 */     String normName = normSection(name);
/*  358 */     if (!hasSection(normName))
/*      */     {
/*  360 */       Section section = new Section(normName, this.commentDelims, this.isCaseSensitive);
/*  361 */       section.setOptionFormat(this.optionFormat);
/*  362 */       this.sections.put(normName, section);
/*  363 */       this.sectionOrder.add(normName);
/*  364 */       return true;
/*      */     }
/*  366 */     return false;
/*      */   }
/*      */ 
/*      */   public boolean removeSection(String name)
/*      */   {
/*  378 */     String normName = normSection(name);
/*  379 */     if ((this.commonName != null) && (this.commonName.equals(normName))) {
/*  380 */       throw new IllegalArgumentException("Can't remove common section");
/*      */     }
/*  382 */     if (hasSection(normName)) {
/*  383 */       this.sections.remove(normName);
/*  384 */       this.sectionOrder.remove(normName);
/*  385 */       return true;
/*      */     }
/*  387 */     return false;
/*      */   }
/*      */ 
/*      */   public List<String> sectionNames()
/*      */   {
/*  398 */     List sectList = new ArrayList(this.sectionOrder);
/*  399 */     if (this.commonName != null) {
/*  400 */       sectList.remove(this.commonName);
/*      */     }
/*  402 */     return sectList;
/*      */   }
/*      */ 
/*      */   public List<String> optionNames(String section)
/*      */   {
/*  414 */     if (hasSection(section)) {
/*  415 */       return getSection(section).optionNames();
/*      */     }
/*  417 */     throw new NoSuchSectionException(section);
/*      */   }
/*      */ 
/*      */   public void addComment(String section, String comment)
/*      */   {
/*  431 */     if (hasSection(section))
/*  432 */       getSection(section).addComment(comment);
/*      */     else
/*  434 */       throw new NoSuchSectionException(section);
/*      */   }
/*      */ 
/*      */   public void addBlankLine(String section)
/*      */   {
/*  445 */     if (hasSection(section))
/*  446 */       getSection(section).addBlankLine();
/*      */     else
/*  448 */       throw new NoSuchSectionException(section);
/*      */   }
/*      */ 
/*      */   public void save(String filename)
/*      */     throws IOException
/*      */   {
/*  459 */     save(new File(filename));
/*      */   }
/*      */ 
/*      */   public void save(File file)
/*      */     throws IOException
/*      */   {
/*  469 */     OutputStream out = new FileOutputStream(file);
/*  470 */     save(out);
/*  471 */     out.close();
/*      */   }
/*      */ 
/*      */   public void save(OutputStream stream)
/*      */     throws IOException
/*      */   {
/*  483 */     save(new OutputStreamWriter(stream));
/*      */   }
/*      */ 
/*      */   public void save(OutputStreamWriter streamWriter)
/*      */     throws IOException
/*      */   {
/*  493 */     Iterator it = this.sectionOrder.iterator();
/*  494 */     PrintWriter writer = new PrintWriter(streamWriter, true);
/*  495 */     while (it.hasNext()) {
/*  496 */       Section sect = getSection((String)it.next());
/*  497 */       writer.println(sect.header());
/*  498 */       sect.save(writer);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void load(String filename)
/*      */     throws IOException
/*      */   {
/*  511 */     load(new File(filename));
/*      */   }
/*      */ 
/*      */   public void load(File file)
/*      */     throws IOException
/*      */   {
/*  523 */     InputStream in = new FileInputStream(file);
/*  524 */     load(in);
/*  525 */     in.close();
/*      */   }
/*      */ 
/*      */   public void load(InputStream stream)
/*      */     throws IOException
/*      */   {
/*  539 */     load(new InputStreamReader(stream));
/*      */   }
/*      */ 
/*      */   public void load(InputStreamReader streamReader)
/*      */     throws IOException
/*      */   {
/*  550 */     BufferedReader reader = new BufferedReader(streamReader);
/*  551 */     String curSection = null;
/*  552 */     String line = null;
/*      */ 
/*  554 */     while (reader.ready()) {
/*  555 */       line = reader.readLine().trim();
/*  556 */       if ((line.length() > 0) && (line.charAt(0) == '[')) {
/*  557 */         int endIndex = line.indexOf(']');
/*  558 */         if (endIndex >= 0) {
/*  559 */           curSection = line.substring(1, endIndex);
/*  560 */           addSection(curSection);
/*      */         }
/*      */       }
/*  563 */       if (curSection != null) {
/*  564 */         Section sect = getSection(curSection);
/*  565 */         sect.load(reader);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private Section getSection(String name)
/*      */   {
/*  577 */     return (Section)this.sections.get(normSection(name));
/*      */   }
/*      */ 
/*      */   private String normSection(String name)
/*      */   {
/*  590 */     if (!this.isCaseSensitive) {
/*  591 */       name = name.toLowerCase();
/*      */     }
/*  593 */     return name.trim();
/*      */   }
/*      */ 
/*      */   private static String[] toStringArray(Collection<Object> coll) {
/*  597 */     Object[] objArray = coll.toArray();
/*  598 */     String[] strArray = new String[objArray.length];
/*  599 */     for (int i = 0; i < objArray.length; i++) {
/*  600 */       strArray[i] = ((String)objArray[i]);
/*      */     }
/*  602 */     return strArray;
/*      */   }
/*      */ 
/*      */   private static class Comment
/*      */     implements IniEditor.Line
/*      */   {
/*      */     private String comment;
/*      */     private char delimiter;
/*      */     private static final char DEFAULT_DELIMITER = '#';
/*      */ 
/*      */     public Comment(String comment)
/*      */     {
/* 1076 */       this(comment, '#');
/*      */     }
/*      */ 
/*      */     public Comment(String comment, char delimiter) {
/* 1080 */       this.comment = comment.trim();
/* 1081 */       this.delimiter = delimiter;
/*      */     }
/*      */ 
/*      */     public String toString() {
/* 1085 */       return this.delimiter + " " + this.comment;
/*      */     }
/*      */   }
/*      */ 
/*      */   private static abstract interface Line
/*      */   {
/*      */     public abstract String toString();
/*      */   }
/*      */ 
/*      */   public static class NoSuchSectionException extends RuntimeException
/*      */   {
/*      */     private static final long serialVersionUID = 1L;
/*      */ 
/*      */     public NoSuchSectionException()
/*      */     {
/*      */     }
/*      */ 
/*      */     public NoSuchSectionException(String msg)
/*      */     {
/* 1161 */       super();
/*      */     }
/*      */   }
/*      */ 
/*      */   private static class Option
/*      */     implements IniEditor.Line
/*      */   {
/*      */     private String name;
/*      */     private String value;
/*      */     private char separator;
/*      */     private IniEditor.OptionFormat format;
/*      */     private static final String ILLEGAL_VALUE_CHARS = "\n\r";
/*      */ 
/*      */     public Option(String name, String value, char separator, IniEditor.OptionFormat format)
/*      */     {
/* 1021 */       if (!validName(name, separator)) {
/* 1022 */         throw new IllegalArgumentException("Illegal option name:" + name);
/*      */       }
/* 1024 */       this.name = name;
/* 1025 */       this.separator = separator;
/* 1026 */       this.format = format;
/* 1027 */       set(value);
/*      */     }
/*      */ 
/*      */     public String name() {
/* 1031 */       return this.name;
/*      */     }
/*      */ 
/*      */     public String value() {
/* 1035 */       return this.value;
/*      */     }
/*      */ 
/*      */     public void set(String value) {
/* 1039 */       if (value == null) {
/* 1040 */         this.value = value;
/*      */       } else {
/* 1042 */         StringTokenizer st = new StringTokenizer(value.trim(), "\n\r");
/* 1043 */         StringBuffer sb = new StringBuffer();
/*      */ 
/* 1045 */         while (st.hasMoreTokens()) {
/* 1046 */           sb.append(st.nextToken());
/*      */         }
/* 1048 */         this.value = sb.toString();
/*      */       }
/*      */     }
/*      */ 
/*      */     public String toString() {
/* 1053 */       return this.format.format(this.name, this.value, this.separator);
/*      */     }
/*      */ 
/*      */     private static boolean validName(String name, char separator) {
/* 1057 */       if (name.trim().equals("")) {
/* 1058 */         return false;
/*      */       }
/*      */ 
/* 1061 */       return name.indexOf(separator) < 0;
/*      */     }
/*      */   }
/*      */ 
/*      */   private static class OptionFormat
/*      */   {
/*      */     private static final int EXPECTED_TOKENS = 4;
/*      */     private String[] formatTokens;
/*      */ 
/*      */     public OptionFormat(String formatString)
/*      */     {
/* 1097 */       this.formatTokens = compileFormat(formatString);
/*      */     }
/*      */ 
/*      */     public String format(String name, String value, char separator) {
/* 1101 */       String[] t = this.formatTokens;
/* 1102 */       return t[0] + name + t[1] + separator + t[2] + value + t[3];
/*      */     }
/*      */ 
/*      */     private String[] compileFormat(String formatString) {
/* 1106 */       String[] tokens = { "", "", "", "" };
/* 1107 */       int tokenCount = 0;
/* 1108 */       boolean seenPercent = false;
/* 1109 */       StringBuffer token = new StringBuffer();
/* 1110 */       for (int i = 0; i < formatString.length(); i++) {
/* 1111 */         switch (formatString.charAt(i)) {
/*      */         case '%':
/* 1113 */           if (seenPercent) {
/* 1114 */             token.append("%");
/* 1115 */             seenPercent = false;
/*      */           } else {
/* 1117 */             seenPercent = true;
/*      */           }
/* 1119 */           break;
/*      */         case 's':
/* 1121 */           if (seenPercent) {
/* 1122 */             if (tokenCount >= 4) {
/* 1123 */               throw new IllegalArgumentException("Illegal option format. Too many %s placeholders.");
/*      */             }
/* 1125 */             tokens[tokenCount] = token.toString();
/* 1126 */             tokenCount++;
/* 1127 */             token = new StringBuffer();
/* 1128 */             seenPercent = false;
/*      */           } else {
/* 1130 */             token.append("s");
/*      */           }
/* 1132 */           break;
/*      */         default:
/* 1134 */           if (seenPercent) {
/* 1135 */             throw new IllegalArgumentException("Illegal option format. Unknown format specifier.");
/*      */           }
/* 1137 */           token.append(formatString.charAt(i));
/*      */         }
/*      */       }
/*      */ 
/* 1141 */       if (tokenCount != 3) {
/* 1142 */         throw new IllegalArgumentException("Illegal option format. Not enough %s placeholders.");
/*      */       }
/* 1144 */       tokens[tokenCount] = token.toString();
/* 1145 */       return tokens;
/*      */     }
/*      */   }
/*      */ 
/*      */   public static class Section
/*      */   {
/*      */     private String name;
/*      */     private Map<String, IniEditor.Option> options;
/*      */     private List<IniEditor.Line> lines;
/*      */     private char[] optionDelims;
/*      */     private char[] optionDelimsSorted;
/*      */     private char[] commentDelims;
/*      */     private char[] commentDelimsSorted;
/*      */     private boolean isCaseSensitive;
/*      */     private IniEditor.OptionFormat optionFormat;
/*  624 */     private static final char[] DEFAULT_OPTION_DELIMS = { '=', ':' };
/*  625 */     private static final char[] DEFAULT_COMMENT_DELIMS = { '#', ';' };
/*  626 */     private static final char[] OPTION_DELIMS_WHITESPACE = { ' ', '\t' };
/*      */     private static final boolean DEFAULT_CASE_SENSITIVITY = false;
/*      */     public static final String DEFAULT_OPTION_FORMAT = "%s %s %s";
/*      */     public static final char HEADER_START = '[';
/*      */     public static final char HEADER_END = ']';
/*      */     private static final int NAME_MAXLENGTH = 1024;
/*  633 */     private static final char[] INVALID_NAME_CHARS = { '[', ']' };
/*      */     private static final String NEWLINE_CHARS = "\n\r";
/*      */ 
/*      */     public Section(String name)
/*      */     {
/*  642 */       this(name, null);
/*      */     }
/*      */ 
/*      */     public Section(String name, boolean isCaseSensitive)
/*      */     {
/*  653 */       this(name, null, isCaseSensitive);
/*      */     }
/*      */ 
/*      */     public Section(String name, char[] delims)
/*      */     {
/*  666 */       this(name, delims, false);
/*      */     }
/*      */ 
/*      */     public Section(String name, char[] delims, boolean isCaseSensitive)
/*      */     {
/*  680 */       if (!validName(name)) {
/*  681 */         throw new IllegalArgumentException("Illegal section name:" + name);
/*      */       }
/*  683 */       this.name = name;
/*  684 */       this.isCaseSensitive = isCaseSensitive;
/*  685 */       this.options = new HashMap();
/*  686 */       this.lines = new LinkedList();
/*  687 */       this.optionDelims = DEFAULT_OPTION_DELIMS;
/*  688 */       this.commentDelims = (delims == null ? DEFAULT_COMMENT_DELIMS : delims);
/*  689 */       this.optionFormat = new IniEditor.OptionFormat("%s %s %s");
/*      */ 
/*  691 */       this.optionDelimsSorted = new char[this.optionDelims.length];
/*  692 */       System.arraycopy(this.optionDelims, 0, this.optionDelimsSorted, 0, this.optionDelims.length);
/*  693 */       this.commentDelimsSorted = new char[this.commentDelims.length];
/*  694 */       System.arraycopy(this.commentDelims, 0, this.commentDelimsSorted, 0, this.commentDelims.length);
/*  695 */       Arrays.sort(this.optionDelimsSorted);
/*  696 */       Arrays.sort(this.commentDelimsSorted);
/*      */     }
/*      */ 
/*      */     public void setOptionFormatString(String formatString)
/*      */     {
/*  719 */       setOptionFormat(new IniEditor.OptionFormat(formatString));
/*      */     }
/*      */ 
/*      */     public void setOptionFormat(IniEditor.OptionFormat format)
/*      */     {
/*  729 */       this.optionFormat = format;
/*      */     }
/*      */ 
/*      */     public List<String> optionNames()
/*      */     {
/*  739 */       List optNames = new LinkedList();
/*  740 */       Iterator it = this.lines.iterator();
/*  741 */       while (it.hasNext()) {
/*  742 */         Object line = it.next();
/*  743 */         if ((line instanceof IniEditor.Option)) {
/*  744 */           optNames.add(((IniEditor.Option)line).name());
/*      */         }
/*      */       }
/*  747 */       return optNames;
/*      */     }
/*      */ 
/*      */     public boolean hasOption(String name)
/*      */     {
/*  757 */       return this.options.containsKey(normOption(name));
/*      */     }
/*      */ 
/*      */     public String get(String option)
/*      */     {
/*  768 */       String normed = normOption(option);
/*  769 */       if (hasOption(normed)) {
/*  770 */         return getOption(normed).value();
/*      */       }
/*  772 */       return null;
/*      */     }
/*      */ 
/*      */     public void set(String option, String value)
/*      */     {
/*  784 */       set(option, value, this.optionDelims[0]);
/*      */     }
/*      */ 
/*      */     public void set(String option, String value, char delim)
/*      */     {
/*  797 */       String normed = normOption(option);
/*  798 */       if (hasOption(normed)) {
/*  799 */         getOption(normed).set(value);
/*      */       }
/*      */       else {
/*  802 */         IniEditor.Option opt = new IniEditor.Option(normed, value, delim, this.optionFormat);
/*  803 */         this.options.put(normed, opt);
/*  804 */         this.lines.add(opt);
/*      */       }
/*      */     }
/*      */ 
/*      */     public boolean remove(String option)
/*      */     {
/*  815 */       String normed = normOption(option);
/*  816 */       if (hasOption(normed)) {
/*  817 */         this.lines.remove(getOption(normed));
/*  818 */         this.options.remove(normed);
/*  819 */         return true;
/*      */       }
/*  821 */       return false;
/*      */     }
/*      */ 
/*      */     public void addComment(String comment)
/*      */     {
/*  833 */       addComment(comment, this.commentDelims[0]);
/*      */     }
/*      */ 
/*      */     public void addComment(String comment, char delim)
/*      */     {
/*  845 */       StringTokenizer st = new StringTokenizer(comment.trim(), "\n\r");
/*  846 */       while (st.hasMoreTokens())
/*  847 */         this.lines.add(new IniEditor.Comment(st.nextToken(), delim));
/*      */     }
/*      */ 
/*      */     public void addBlankLine()
/*      */     {
/*  857 */       this.lines.add(IniEditor.BLANK_LINE);
/*      */     }
/*      */ 
/*      */     public void load(BufferedReader reader)
/*      */       throws IOException
/*      */     {
/*  869 */       while (reader.ready()) {
/*  870 */         reader.mark(1024);
/*  871 */         String line = reader.readLine().trim();
/*      */ 
/*  874 */         if ((line.length() > 0) && (line.charAt(0) == '[')) {
/*  875 */           reader.reset();
/*  876 */           return;
/*      */         }
/*      */ 
/*  879 */         int delimIndex = -1;
/*      */ 
/*  881 */         if (line.equals("")) {
/*  882 */           addBlankLine();
/*      */         }
/*  885 */         else if ((delimIndex = Arrays.binarySearch(this.commentDelimsSorted, line.charAt(0))) >= 0) {
/*  886 */           addComment(line.substring(1), this.commentDelimsSorted[delimIndex]);
/*      */         }
/*      */         else
/*      */         {
/*  890 */           delimIndex = -1;
/*  891 */           int delimNum = -1;
/*  892 */           int lastSpaceIndex = -1;
/*  893 */           int i = 0; for (int l = line.length(); (i < l) && (delimIndex < 0); i++) {
/*  894 */             delimNum = Arrays.binarySearch(this.optionDelimsSorted, line.charAt(i));
/*  895 */             if (delimNum >= 0) {
/*  896 */               delimIndex = i;
/*      */             } else {
/*  898 */               boolean isSpace = Arrays.binarySearch(OPTION_DELIMS_WHITESPACE, line.charAt(i)) >= 0;
/*  899 */               if ((!isSpace) && (lastSpaceIndex >= 0))
/*      */                 break;
/*  901 */               if (isSpace) {
/*  902 */                 lastSpaceIndex = i;
/*      */               }
/*      */             }
/*      */           }
/*      */ 
/*  907 */           if (delimIndex == 0)
/*      */           {
/*      */             continue;
/*      */           }
/*  911 */           if (delimIndex < 0) {
/*  912 */             if (lastSpaceIndex < 0)
/*  913 */               set(line, "");
/*      */             else {
/*  915 */               set(line.substring(0, lastSpaceIndex), line.substring(lastSpaceIndex + 1));
/*      */             }
/*      */ 
/*      */           }
/*      */           else
/*      */           {
/*  921 */             set(line.substring(0, delimIndex), line.substring(delimIndex + 1), line.charAt(delimIndex));
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/*      */     public void save(PrintWriter writer)
/*      */       throws IOException
/*      */     {
/*  934 */       Iterator it = this.lines.iterator();
/*  935 */       while (it.hasNext()) {
/*  936 */         writer.println(((IniEditor.Line)it.next()).toString());
/*      */       }
/*  938 */       if (writer.checkError())
/*  939 */         throw new IOException();
/*      */     }
/*      */ 
/*      */     private IniEditor.Option getOption(String name)
/*      */     {
/*  951 */       return (IniEditor.Option)this.options.get(name);
/*      */     }
/*      */ 
/*      */     private String header()
/*      */     {
/*  961 */       return '[' + this.name + ']';
/*      */     }
/*      */ 
/*      */     private static boolean validName(String name)
/*      */     {
/*  973 */       if (name.trim().equals("")) {
/*  974 */         return false;
/*      */       }
/*  976 */       for (int i = 0; i < INVALID_NAME_CHARS.length; i++) {
/*  977 */         if (name.indexOf(INVALID_NAME_CHARS[i]) >= 0) {
/*  978 */           return false;
/*      */         }
/*      */       }
/*  981 */       return true;
/*      */     }
/*      */ 
/*      */     private String normOption(String name)
/*      */     {
/*  993 */       if (!this.isCaseSensitive) {
/*  994 */         name = name.toLowerCase();
/*      */       }
/*  996 */       return name.trim();
/*      */     }
/*      */   }
/*      */ }

/* Location:           D:\Ellias\联盟程序\进攻ogzq-sb按11回车\classes\
 * Qualified Name:     com.ogzq.util.IniEditor
 * JD-Core Version:    0.6.0
 */