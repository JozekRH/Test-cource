package ru.progwards.java1.SeaBattle;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import ru.progwards.lms.main.CompetitionHelper;
import ru.progwards.lms.main.CompetitionResult;

public class CompetitionRunner {
	private static final String className = "BattleAlg";
	private static final String methodName = "test";
	
	public static String run(String[] students) {
		ArrayList<CompetitionResult> results =  new ArrayList<CompetitionResult>();
		for(String name : students)
			results.add(new CompetitionResult(name, runOne(name)));
 		
		return CompetitionHelper.getResultJSON("������� ���", results);  
	}

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static double runOne(String name) {
		String pname = CompetitionRunner.class.getName();
		int n = pname.lastIndexOf(".");
		pname = pname.substring(0, n+1)+name;
		try {
			// �������� ������ �� �����
			Class cls = Class.forName(pname+"."+className);
			// ����� ������������ ��� ����������
			Class[] emptyParam = {};
			Object alg = cls.getConstructor(emptyParam).newInstance();
			// ����� ������ � �����������
			Class[] paramTypes = new Class[] {String.class};
			Object[] args = new Object[]{new String("3")};
			return (Double)cls.getMethod(methodName, paramTypes).invoke(alg, args);
	   } catch (InstantiationException | IllegalAccessException | ClassNotFoundException |
			   NoSuchMethodException | InvocationTargetException e) {
	       //e.printStackTrace();
		   return 0.0;
	   }
	}

	public static void main (String[] args) 
	{
		String[] students = { "pvasechkin", "iivanov", "vpupkin" };
		System.out.println(run(students));
	} 
}
