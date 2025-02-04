package cafe_luck;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class main_cafe extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameField;
	private JComboBox<String> firstDishCombo, secondDishCombo, drinkCombo;
	private JRadioButton regularCustomerRadio;
	private JTextArea resultArea;

	private String[] firstDishes = {"Салат Цезарь", "Борщ", "Суп с фрикадельками"};
	private String[] secondDishes = {"Паста", "Пельмени", "Стейк"};
	private String[] drinks = {"Кофе", "Чай", "Сок"};
	private double[] firstDishPrices = {500, 400, 350};
	private double[] secondDishPrices = {800, 600, 120};
	private double[] drinkPrices = {250, 150, 300};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main_cafe frame = new main_cafe();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public main_cafe() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1182, 674);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(6, 2, 5, 5));
		
		// Поле для ввода имени
				contentPane.add(new JLabel("Имя клиента:"));
				nameField = new JTextField();
				contentPane.add(nameField);

				// Выбор первого блюда
				contentPane.add(new JLabel("Первое блюдо:"));
				firstDishCombo = new JComboBox<>(firstDishes);
				contentPane.add(firstDishCombo);

				// Выбор второго блюда
				contentPane.add(new JLabel("Второе блюдо:"));
				secondDishCombo = new JComboBox<>(secondDishes);
				contentPane.add(secondDishCombo);

				// Выбор напитка
				contentPane.add(new JLabel("Напиток:"));
				drinkCombo = new JComboBox<>(drinks);
				contentPane.add(drinkCombo);

				// Радиокнопка для постоянного клиента
				regularCustomerRadio = new JRadioButton("Постоянный клиент");
				contentPane.add(regularCustomerRadio);
				contentPane.add(new JLabel()); // Пустой элемент для выравнивания

				// Кнопка для вывода результатов
				JButton calculateButton = new JButton("Показать заказ");
				contentPane.add(calculateButton);

				// Поле для вывода результата с увеличенным JScrollPane
				resultArea = new JTextArea();
				resultArea.setEditable(false);
				JScrollPane scrollPane = new JScrollPane(resultArea);
				scrollPane.setPreferredSize(new Dimension(450, 100)); // Увеличиваем размер прокручиваемой области
				contentPane.add(scrollPane);

				// Обработчик события для кнопки
				calculateButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						showOrder();
					}
				});
	}

	private void showOrder() {
		String name = nameField.getText().trim();
		if (name.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Пожалуйста, введите ваше имя.", "Ошибка", JOptionPane.ERROR_MESSAGE);
			return; // Прекратить выполнение, если имя не введено
		}
		
		int firstDishIndex = firstDishCombo.getSelectedIndex();
		int secondDishIndex = secondDishCombo.getSelectedIndex();
		int drinkIndex = drinkCombo.getSelectedIndex();

		double total = firstDishPrices[firstDishIndex] +
					   secondDishPrices[secondDishIndex] +
					   drinkPrices[drinkIndex];

		// Скидка для постоянного клиента
		if (regularCustomerRadio.isSelected()) {
			total *= 0.9; // 10% скидка
		}

		// Вывод результата
		String result = "Здравствуйте, " + name + "!\n" +
				"Ваш заказ:\n" +
				"Первое блюдо: " + firstDishes[firstDishIndex] + " - " + firstDishPrices[firstDishIndex] + " рублей\n" +
				"Второе блюдо: " + secondDishes[secondDishIndex] + " - " + secondDishPrices[secondDishIndex] + " рублей\n" +
				"Напиток: " + drinks[drinkIndex] + " - " + drinkPrices[drinkIndex] + " рублей\n" +
				(regularCustomerRadio.isSelected() ? "Постоянный клиент\n" : "Новый клиент\n") +
				"Общая стоимость: " + String.format("%.2f", total) + " рублей";
		
		resultArea.setText(result);
	}
}
