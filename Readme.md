
## Условие:
Написать на Java программу распаковывания строки. На вход поступает строка вида число[строка], на выход - строка, содержащая повторяющиеся подстроки.

## Пример:
Вход: `3[xyz]4[xy]z`
Выход: `xyzxyzxyzxyxyxyxyz`
## Ограничения:
- одно повторение может содержать другое. 
  Например: `2[3[x]y]  = xxxyxxxy`
- допустимые символы на вход: латинские буквы, числа и скобки []
- числа означают только число повторений
- скобки только для обозначения повторяющихся подстрок
- входная строка всегда валидна.
## Дополнительное задание:
Проверить входную строку на валидность.
## Комментарий:
Программа запускается из класса `StringTask`.
Основной алгоритм был реализован за достаточно короткое время и занимал не более 70 строк, после чего вносились различные правки и расширение функционала.
С целью инкапсуляции был создан класс `Reformer`, где в случае создания экземпляра данного класса у нас открывается возможность использовать методы:
- `isValid(String str)` - в случае валидности строки возвращает `true`
- `convert(String str)` - возвращает видоизмененную строку по условию задания.

Кроме того возможно использование двух- и более значных чисел.