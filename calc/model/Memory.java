package calc.model;

import java.util.ArrayList;
import java.util.List;

public class Memory {
	
	private enum TypeCommand {
		CLEAR, SIGN, NUMBER, DIVISION, MULTIPLICATION, SUM, SUBTRACTION, COMMA, EQUALS;
	}
	
	private static final Memory instance = new Memory();
	private boolean substitute = false;
	private TypeCommand lastOp = null;
	private String initText = "";
	private String bufferText = "";
	private final List<MemoryObserver> observers = new ArrayList<>();
	
	private Memory() {}

	public static Memory getInstance() {
		return instance;
	}
	
	public void addObserver(MemoryObserver ob) {
		observers.add(ob);
	}

	public String getPresentText() {
		return initText.isEmpty() ? "0" : initText;
	}
	
	public void processCommand(String value) {
		
		TypeCommand type = detectTypeCommand(value);
		
		if(type == null) {
			return;
		} else if(type == TypeCommand.CLEAR) {
			initText = "";
			bufferText = "";
			substitute = false;
			lastOp = null;
		} else if(type== TypeCommand.SIGN && initText.contains("-")) {
			initText = initText.substring(1);
		} else if(type == TypeCommand.SIGN && !initText.contains("-")) {
			initText = "-" + initText;
		} else if(type == TypeCommand.NUMBER || type == TypeCommand.COMMA) {
			initText = substitute ? value : initText + value;
			substitute = false;
		} else {
			substitute = true;
			initText = getResultOfOperation();
			bufferText = initText;
			lastOp = type;
		}
		
		observers.forEach(o -> o.valueChange(getPresentText()));
	}
	
	private String getResultOfOperation() {
		
		if(lastOp == null || lastOp == TypeCommand.EQUALS) {
			return initText;
		}
		
		double numberBuffer = Double.parseDouble(bufferText.replace(",", "."));
		double numberActual = Double.parseDouble(initText.replace(",", "."));
		
		double result = 0;
		
		if(lastOp == TypeCommand.SUM) {
			result = numberBuffer + numberActual;
		} else if(lastOp == TypeCommand.SUBTRACTION) {
			result = numberBuffer - numberActual;
		} else if(lastOp == TypeCommand.MULTIPLICATION) {
			result = numberBuffer * numberActual;
		} else if(lastOp == TypeCommand.DIVISION) {
			result = numberBuffer / numberActual;
		} 
		
		String resultStr = Double.toString(result).replace(".", ",");
		boolean wholeNum = resultStr.endsWith(",0");
		return wholeNum ? resultStr.replace(",0", "") : resultStr;
	}

	private TypeCommand detectTypeCommand(String value) {
		
		if(initText.isEmpty() && value == "0") {
			return null;
		}
		
		try {
			Integer.parseInt(value);
			return TypeCommand.NUMBER;
		} catch (NumberFormatException e) {
			// When it's not a number...
			if("AC".equals(value)) {
				return TypeCommand.CLEAR;
			} else if("÷".equals(value)) {
				return TypeCommand.DIVISION;
			} else if("*".equals(value)) {
				return TypeCommand.MULTIPLICATION;
			} else if("+".equals(value)) {
				return TypeCommand.SUM;	
			} else if("-".equals(value)) {
				return TypeCommand.SUBTRACTION;
			} else if("=".equals(value)) {
				return TypeCommand.EQUALS;
			} else if(",".equals(value) && !initText.contains(",")) {
				return TypeCommand.COMMA;
			} else if("±".equals(value)) {
				return TypeCommand.SIGN;
			}
				
			return null;
		}
	}
}
