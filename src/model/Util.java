package model;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Util {

    public static void addMask(final TextField tf, final String mask) {
        tf.setText(mask);
        addTextLimiter(tf, mask.length());

        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue,
                                final String newValue) {
                String value = stripMask(tf.getText(), mask);
                tf.setText(merge(value, mask));
            }
        });

        tf.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(final KeyEvent e) {
                int caretPosition = tf.getCaretPosition();
                if (caretPosition < mask.length() - 1 && mask.charAt(caretPosition) != ' '
                        && e.getCode() != KeyCode.BACK_SPACE && e.getCode() != KeyCode.LEFT) {
                    tf.positionCaret(caretPosition + 1);
                }
            }
        });
    }

    public static void addMaskCPF(final TextField tf, final String mask) {
        tf.setText(mask);
        addTextLimiter(tf, mask.length());
        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue,
                                final String newValue) {
                String value = stripMask(tf.getText(), mask);
                tf.setText(merge(value, mask));
            }
        });

        tf.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(final KeyEvent e) {
                int caretPosition = tf.getCaretPosition();
                if (caretPosition < mask.length() - 1 && mask.charAt(caretPosition) != ' '
                        && e.getCode() != KeyCode.BACK_SPACE && e.getCode() != KeyCode.LEFT) {
                    tf.positionCaret(caretPosition + 1);
                }
            }
        });
    }

    public static void addMaskCNPJ(final TextField tf, final String mask) {
        tf.setText(mask);
        addTextLimiter(tf, mask.length());
        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue,
                                final String newValue) {
                String value = stripMask(tf.getText(), mask);
                tf.setText(merge(value, mask));
            }
        });

        tf.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(final KeyEvent e) {
                int caretPosition = tf.getCaretPosition();
                if (caretPosition < mask.length() - 1 && mask.charAt(caretPosition) != ' '
                        && e.getCode() != KeyCode.BACK_SPACE && e.getCode() != KeyCode.LEFT) {
                    tf.positionCaret(caretPosition + 1);
                }
            }
        });
    }

    public static void addMaskDate(final TextField tf, final String mask) {
        tf.setText(mask);
        addTextLimiter(tf, mask.length());
        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue,
                                final String newValue) {
                String value = stripMask(tf.getText(), mask);
                tf.setText(merge(value, mask));
            }
        });

        tf.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(final KeyEvent e) {
                int caretPosition = tf.getCaretPosition();
                if (caretPosition < mask.length() - 1 && mask.charAt(caretPosition) != ' '
                        && e.getCode() != KeyCode.BACK_SPACE && e.getCode() != KeyCode.LEFT) {
                    tf.positionCaret(caretPosition + 1);
                }
            }
        });
    }

    public static void addMaskInteiro(final TextField tf, int tamanho) {
        addTextLimiter(tf, tamanho);
        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue,
                                final String newValue) {
                if (!newValue.equals("")) {
                    tf.setText(tf.getText());
                } else {
                    if (tf.getText().length() > 0) {
                        tf.setText(tf.getText().substring(0, tf.getText().length() - 1));
                    }
                }
            }
        });
    }

    static String merge(final String value, final String mask) {
        final StringBuilder sb = new StringBuilder(mask);
        int k = 0;
        for (int i = 0; i < mask.length(); i++) {
            if (mask.charAt(i) == ' ' && k < value.length()) {
                sb.setCharAt(i, value.charAt(k));
                k++;
            }
        }
        return sb.toString();
    }

    static String stripMask(String text, final String mask) {
        final Set<String> maskChars = new HashSet<>();
        for (int i = 0; i < mask.length(); i++) {
            char c = mask.charAt(i);
            if (c != ' ') {
                maskChars.add(String.valueOf(c));
            }
        }
        for (String c : maskChars) {
            text = text.replace(c, "");
        }
        return text;
    }

    public static void addTextLimiter(final TextField tf, final int maxLength) {
        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue,
                                final String newValue) {
                if (tf.getText().length() > maxLength) {
                    String s = tf.getText().substring(0, maxLength);
                    tf.setText(s);
                }
            }
        });

    }

    public static void dateField(final TextField textField) {
        addTextLimiter(textField, 10);

        textField.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.intValue() < 11) {
                    String value = textField.getText();
                    value = value.replaceAll("[^0-9]", "");
                    value = value.replaceFirst("(\\d{2})(\\d)", "$1/$2");
                    value = value.replaceFirst("(\\d{2})\\/(\\d{2})(\\d)", "$1/$2/$3");
                    textField.setText(value);
                }
            }
        });
    }

    public static void monetaryField(final TextField textField) {

        textField.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                String value = textField.getText();
                value = value.replaceAll("[^0-9]", "");
                value = value.replaceAll("([0-9]{1})([0-9]{14})$", "$1$2");
                value = value.replaceAll("([0-9]{1})([0-9]{11})$", "$1$2");
                value = value.replaceAll("([0-9]{1})([0-9]{8})$", "$1$2");
                value = value.replaceAll("([0-9]{1})([0-9]{5})$", "$1$2");
                value = value.replaceAll("([0-9]{1})([0-9]{2})$", "$1.$2");

                textField.setText(value);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        textField.positionCaret(textField.getText().length());
                    }
                });
            }
        });

        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if (newValue.length() > 17)
                    textField.setText(oldValue);
            }
        });

        textField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean,
                                Boolean fieldChange) {
                if (!fieldChange) {
                    final int length = textField.getText().length();
                    if (length > 0 && length < 3) {
                        textField.setText(textField.getText() + "00");
                    }
                }
            }
        });
    }

    public static void addTextLimiter(final TextArea ta, final int maxLength) {
        ta.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue,
                                final String newValue) {
                if (ta.getText().length() > maxLength) {
                    String s = ta.getText().substring(0, maxLength);
                    ta.setText(s);
                }
            }
        });

    }

    public static String adicionarZeros(String param) {

        if (param.length() < 2) {
            return "0" + param;
        } else {
            return param;
        }
    }

    public static String converterDataParaStringSemHora(Date data) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);

        String dia = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        String mes = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        String ano = String.valueOf(calendar.get(Calendar.YEAR));

        StringBuilder retorno = new StringBuilder();

        retorno.append(adicionarZeros(dia));
        retorno.append("/");
        retorno.append(adicionarZeros(mes));
        retorno.append("/");
        retorno.append(adicionarZeros(ano));

        return retorno.toString();
    }
}
