select U.NAME,sum(T.amount) as BALANCE 
from Users U 
LEFT JOIN Transactions T 
on U.account=T.account
group by U.name
Having sum(T.amount) >10000;