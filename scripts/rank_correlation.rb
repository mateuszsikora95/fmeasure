require 'csv'

pairs = [
  ['1', 'first'], ['1', 'solr-select1'], ['first', 'solr-select1'],
  ['2', 'second'], ['2', 'solr-select2'], ['second', 'solr-select2']
]

pairs.each do |pair|
  puts "Pair: #{pair.inspect}"
  rank_1 = CSV.open("csv/#{pair[0]}.csv").to_h
  rank_2 = CSV.open("csv/#{pair[1]}.csv").to_h

  in_both_ranks = rank_1.keys & rank_2.keys
  n = in_both_ranks.size
  puts "Same results: #{n}"

  diff_squared_sum = in_both_ranks.each_with_object([]) do |key, acc|
    diff = rank_1[key].to_i - rank_2[key].to_i
    diff_squared = diff ** 2
    acc << diff_squared
  end.sum

  correlation = 1 - (6 * diff_squared_sum).to_f / (n * (n ** 2 - 1))
  puts "Correlation for this pair: #{correlation}"
  puts
end